package com.example.whattoeat.ui.theme.screens.saveRecipe

import com.example.whattoeat.data.daos.SaveRecipeUserDao


sealed class SaveRecipeUIState {
    class Success(val recipes: SaveRecipeUserDao.RecipeSave): SaveRecipeUIState()
    data object Loading : SaveRecipeUIState()
    class Error (val exception: Exception): SaveRecipeUIState()
}