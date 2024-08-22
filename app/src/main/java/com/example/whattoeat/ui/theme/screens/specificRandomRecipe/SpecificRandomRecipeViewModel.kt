package com.example.whattoeat.ui.theme.screens.specificRandomRecipe

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.DataStoreResult
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltViewModel
class SpecificRandomRecipeViewModel @Inject constructor(private val _saveRecipeRepository: SaveRecipeRepository) :
    ViewModel() {

    private val _isSaveRecipeUIState =
        MutableStateFlow<IsSaveRecipeUIState>(IsSaveRecipeUIState.Loading)
    val randomRecipeUIState: StateFlow<IsSaveRecipeUIState> = _isSaveRecipeUIState.asStateFlow()


    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
        val saveRecipe =
            SaveRecipeEntity(idRecipe = recipe.id, title = recipe.title, image = recipe.image)

        if (_saveRecipeRepository.isSaveRecipe(recipe.id).first()) {
            _saveRecipeRepository.deleteOneSaveRecipe(recipe.id)
        } else {
            _saveRecipeRepository.insertOneRecipe(saveRecipe)
        }
    }

//        fun isSaveRecipe(recipe: Recipe) {
//        viewModelScope.launch {
////            _saveRecipeRepository.isSaveRecipe(recipe.id).first { dataStoreResult ->
////
////            }
//
//        }
//    }
    suspend fun isSaveRecipe(recipe: Recipe) = _saveRecipeRepository.isSaveRecipe(recipe.id).first()

}
