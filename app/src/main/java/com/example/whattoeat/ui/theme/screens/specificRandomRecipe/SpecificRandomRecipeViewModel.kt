package com.example.whattoeat.ui.theme.screens.specificRandomRecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import com.example.whattoeat.models.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpecificRandomRecipeViewModel @Inject constructor(private val _saveRecipeRepository: SaveRecipeRepository) :
    ViewModel() {

    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
        val saveRecipe =
            SaveRecipeEntity(idRecipe = recipe.id, title = recipe.title, image = recipe.image)
        _saveRecipeRepository.insertOneRecipe(saveRecipe)
    }
}