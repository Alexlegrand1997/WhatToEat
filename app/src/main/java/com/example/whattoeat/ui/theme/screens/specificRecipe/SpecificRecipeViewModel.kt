package com.example.whattoeat.ui.theme.screens.specificRecipe

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import com.example.whattoeat.data.repositories.SpecificRecipeRepository
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
    savedStateHandle: SavedStateHandle = SavedStateHandle(mapOf())
) : ViewModel() {
    private val _specificRecipeUIState =
        MutableStateFlow<SpecificRecipeUIState>(SpecificRecipeUIState.Loading)
    val specificRecipeUIState: StateFlow<SpecificRecipeUIState> =
        _specificRecipeUIState.asStateFlow()

    private val _specificRecipeRepository = SpecificRecipeRepository()
//    private val idRecipe: String = checkNotNull(savedStateHandle["idRecipe"])

    companion object {
        var idRecipe: String = ""
    }

    private val idRecipe1: String = savedStateHandle[idRecipe] ?: ""


    init {
        if (idRecipe1 !="") {
            getSpecificRecipe(idRecipe1)
        }
    }

    private fun getSpecificRecipe(idRecipe: String) {
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