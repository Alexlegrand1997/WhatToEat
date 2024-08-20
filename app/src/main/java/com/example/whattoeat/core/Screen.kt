package com.example.whattoeat.core

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.whattoeat.R
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeScreen

sealed class Screen (val screen: String) {
        data object Home : Screen("HomeScreen")
        data object SaveRecipe : Screen("SaveRecipeScreen")
        data object RandomRecipe : Screen("RandomRecipeScreen")
        data object SpecificRecipe : Screen("SpecificRecipeScreen")
        data object Setting : Screen("SettingScreen")
}
