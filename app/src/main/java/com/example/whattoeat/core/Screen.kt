package com.example.whattoeat.core

sealed class Screen (val screen: String) {
        data object Home : Screen("HomeScreen")
        data object SaveRecipe : Screen("SaveRecipeScreen")
        data object RandomRecipe : Screen("RandomRecipeScreen")
}