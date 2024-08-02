package com.example.whattoeat.ui.theme.screens.specificRecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.core.CurrentSpecificRecipe
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import com.example.whattoeat.data.repositories.SpecificRecipeRepository
import com.example.whattoeat.models.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecificRecipeViewModel @Inject constructor(
    private val _saveRecipeRepository: SaveRecipeRepository,

) : ViewModel() {
    private val _specificRecipeUIState =
        MutableStateFlow<SpecificRecipeUIState>(SpecificRecipeUIState.Loading)
    val specificRecipeUIState: StateFlow<SpecificRecipeUIState> =
        _specificRecipeUIState.asStateFlow()

    private val _specificRecipeRepository = SpecificRecipeRepository()

    fun getSpecificRecipe(idRecipe: String) {
        if (!idRecipe.isNullOrEmpty() && idRecipe != CurrentSpecificRecipe.getRecipeId()) {
            CurrentSpecificRecipe.setRecipeId(idRecipe)
            viewModelScope.launch {
                _specificRecipeRepository.getOneRecipe(idRecipe).collect { apiResult ->
                    when (apiResult) {
                        is ApiResult.Error -> {
                            _specificRecipeUIState.update {
                                SpecificRecipeUIState.Error(
                                    IllegalStateException(
                                        apiResult.throwable
                                    )
                                )
                            }
                        }
                        ApiResult.Loading -> SpecificRecipeUIState.Loading
                        is ApiResult.Success -> _specificRecipeUIState.update {
                            SpecificRecipeUIState.Success(apiResult.data)
                        }
                    }
                }
            }

        }
    }

    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
        val saveRecipe =
            SaveRecipeEntity(idRecipe = recipe.id, title = recipe.title, image = recipe.image)
        _saveRecipeRepository.insertOneRecipe(saveRecipe)
    }

}