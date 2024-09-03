package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.Screen
import com.example.whattoeat.ui.theme.screens.home.HomeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeViewModel
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeScreen
import com.example.whattoeat.ui.theme.screens.setting.SettingScreen
import com.example.whattoeat.ui.theme.screens.specificRandomRecipe.SpecificRandomRecipeScreen
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeScreen
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeViewModel


// https://itnext.io/navigation-bar-bottom-app-bar-in-jetpack-compose-with-material-3-c57ae317bd00
// https://johncodeos.com/how-to-create-bottom-navigation-bar-with-jetpack-compose/
//https://medium.com/@bharadwaj.rns/bottom-navigation-in-jetpack-compose-using-material3-c153ccbf0593
@Composable
fun BottomNavBar(
    application: WhatToEatApplication,
//    settingViewModel: SettingViewModel,
//    saveRecipeViewModel: SaveRecipeViewModel,
    randomRecipeViewModel: RandomRecipeViewModel,
    specificRecipeViewModel: SpecificRecipeViewModel
) {
    val bottomNavItems = listOf(
        NavigationItem.Home,
        NavigationItem.SaveRecipe,
        NavigationItem.RandomRecipe,
        NavigationItem.Setting
    )


    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },

                        icon = { Icon(imageVector = item.icon, contentDescription = item.title) })
                }

            }
        }, content = { paddingValues ->

            NavHost(
                navController = navController,
                startDestination = Screen.Home.screen,
                modifier = Modifier.padding((paddingValues))
            ) {
                composable(Screen.Home.screen) { HomeScreen() }
                composable(Screen.RandomRecipe.screen) {
                    RandomRecipeScreen(
                        application,
                        randomRecipeViewModel = randomRecipeViewModel,
                        navController = navController
                    )
                }
                composable(Screen.SaveRecipe.screen) {
                    SaveRecipeScreen(
//                        saveRecipeViewModel = saveRecipeViewModel,
                        // TODO NOT A GOOD PRACTICE TO GIVE NAVCONTROLLER. SHOULD EXPOSE AN EVENT : https://developer.android.com/guide/navigation/use-graph/navigate?hl=fr
                        navController = navController
                    )
                }

                composable(
                    "${Screen.SpecificRecipe.screen}/{idRecipe}",
                    arguments = listOf(navArgument("idRecipe")
                    {
                        type = NavType.StringType
                        defaultValue = ""
                    }),
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString("idRecipe")
                        ?.let { specificRecipeViewModel.getSpecificRecipe(it) }
                    SpecificRecipeScreen(
                        application,
                        specificRecipeViewModel = specificRecipeViewModel
                    )

                }

                composable(Screen.Setting.screen) { SettingScreen() }

                // TODO : Find a better way to pass the recipe than saving it in a data object
                composable(Screen.SpecificRandomRecipe.screen) {
                    SpecificRandomRecipeScreen(application)
                }
            }
        }
    )
}



sealed class NavigationItem(var route:String, var icon: ImageVector, var title: String){
    data object Home : NavigationItem("HomeScreen", Icons.Default.Home,"Home")
    data object SaveRecipe : NavigationItem("SaveRecipeScreen", Icons.Default.Favorite,"SaveRecipe")
    data object RandomRecipe : NavigationItem("RandomRecipeScreen", Icons.Default.Refresh,"RandomRecipe")
    data object Setting : NavigationItem("SettingScreen", Icons.Default.Settings,"Setting")
}