package com.example.whattoeat.ui.theme.screens.saveRecipe

import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.models.Recipe


sealed class SaveRecipeUIState {
    class Success(val recipes: List<SaveRecipeEntity>): SaveRecipeUIState()
    data object Loading : SaveRecipeUIState()
    class Error (val exception: Exception): SaveRecipeUIState()
}