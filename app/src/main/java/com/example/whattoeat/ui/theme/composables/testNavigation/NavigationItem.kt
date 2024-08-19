package com.example.whattoeat.ui.theme.composables.testNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavigationItem(var route:String, var icon: ImageVector, var title: String){
    data object Home : NavigationItem("HomeScreen", Icons.Default.Home,"Home")
    data object SaveRecipe : NavigationItem("SaveRecipeScreen", Icons.Default.Favorite,"SaveRecipe")
    data object RandomRecipe : NavigationItem("RandomRecipeScreen", Icons.Default.Refresh,"RandomRecipe")
    data object Setting : NavigationItem("SettingScreen", Icons.Default.Settings,"Setting")
}