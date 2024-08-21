package com.example.whattoeat.ui.theme.screens.specificRandomRecipe

import com.example.whattoeat.models.Recipe

sealed class SpecificRandomRecipeUIState {
    class Success(val recipe: Recipe) : SpecificRandomRecipeUIState()
    data object Loading : SpecificRandomRecipeUIState()
    class Error(val exception: Exception) : SpecificRandomRecipeUIState()
}