package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.whattoeat.Screen


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.SaveRecipe,
        BottomNavItem.RandomRecipe

    )

    NavigationBar {
        items.forEach {
            AddItem(screen = it, navController)
        }
    }

}

@Composable
fun RowScope.AddItem(screen: BottomNavItem, navController: NavController) {
    NavigationBarItem(
        label = { Text(text = screen.title) },
        selected = true,
        onClick = { navController.navigate(screen.route) },
        icon = { },
        colors = NavigationBarItemDefaults.colors()
    )
}

sealed class BottomNavItem(var title: String, var route: String) {
    data object Home : BottomNavItem("Home", "HomeScreen")
    data object SaveRecipe : BottomNavItem("Save Recipe", "SaveRecipeScreen")
    data object RandomRecipe : BottomNavItem("Random Recipe", "RandomRecipeScreen")

}