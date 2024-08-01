package com.example.whattoeat.ui.theme.screens.saveRecipe

import com.example.whattoeat.data.entities.RecipeSaveEntity


sealed class SaveRecipeUIState {
    class Success(val recipes: RecipeSaveEntity): SaveRecipeUIState()
    data object Loading : SaveRecipeUIState()
    class Error (val exception: Exception): SaveRecipeUIState()
}