package com.example.whattoeat.core

import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeScreen

sealed class Screen (val screen: String) {
        data object Home : Screen("HomeScreen")
        data object SaveRecipe : Screen("SaveRecipeScreen")
        data object RandomRecipe : Screen("RandomRecipeScreen")
        data object SpecificRecipe : Screen("SpecificRecipeScreen")
        data object Setting : Screen("SettingScreen")
}