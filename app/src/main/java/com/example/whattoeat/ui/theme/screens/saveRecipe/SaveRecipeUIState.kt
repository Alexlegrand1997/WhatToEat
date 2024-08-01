package com.example.whattoeat.ui.theme.screens.saveRecipe

import com.example.whattoeat.data.entities.SaveRecipeEntity


sealed class SaveRecipeUIState {
    class Success(val recipes: SaveRecipeEntity): SaveRecipeUIState()
    data object Loading : SaveRecipeUIState()
    class Error (val exception: Exception): SaveRecipeUIState()
}