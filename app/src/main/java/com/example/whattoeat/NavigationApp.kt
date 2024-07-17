package com.example.whattoeat

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.whattoeat.ui.theme.composables.BottomNavigationBar
import com.example.whattoeat.ui.theme.screens.home.HomeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeScreen
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeScreen
import com.example.whattoeat.ui.theme.screens.temp.TempScreen

//https://nameisjayant.medium.com/nested-navigation-in-jetpack-compose-597ecdc6eebb

@Composable
fun NavigationApp(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Screen.TempScreen.route
    )
    {
        composable(Screen.TempScreen.route, content = { TempScreen(navController) })
        navigation(
            route = Screen.TempScreen.route,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                BottomNavScreen(navController) {
                    HomeScreen(navController)

                }
            }
            composable(Screen.SaveRecipe.route) {
                BottomNavScreen(navController) {
                    SaveRecipeScreen(navController)

                }
            }
            composable(Screen.RandomRecipe.route) {
                BottomNavScreen(navController) {
                    RandomRecipeScreen(navController)
                }
            }
        }
    }

}

@Composable
fun BottomNavScreen(navController: NavHostController, content: @Composable () -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

sealed class Screen(val title: String, val route: String) {
    object TempScreen: Screen("TempScreen", "TempScreen")
    object Home : Screen("Home", "HomeScreen")
    object SaveRecipe : Screen("Save Recipe", "SaveRecipeScreen")
    object RandomRecipe : Screen("Random Recipe", "RandomRecipeScreen")
}