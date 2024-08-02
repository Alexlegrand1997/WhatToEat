package com.example.whattoeat.ui.theme.screens.specificRecipe

import com.example.whattoeat.models.Recipe

sealed class SpecificRecipeUIState {
    class Success(val recipe: Recipe) : SpecificRecipeUIState()
    data object Loading : SpecificRecipeUIState()
    class Error(val exception: Exception) : SpecificRecipeUIState()
}