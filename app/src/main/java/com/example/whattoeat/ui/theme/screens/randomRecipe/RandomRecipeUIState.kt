package com.example.whattoeat.ui.theme.screens.randomRecipe

import com.example.whattoeat.models.Recipes

sealed class RandomRecipeUIState {
    class Success(val recipes: Recipes): RandomRecipeUIState()
    data object Loading : RandomRecipeUIState()
    class Error (val exception: Exception): RandomRecipeUIState()
}