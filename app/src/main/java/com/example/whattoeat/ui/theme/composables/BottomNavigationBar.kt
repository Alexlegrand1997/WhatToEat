package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.Screen
import com.example.whattoeat.ui.theme.screens.home.HomeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeViewModel
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeScreen
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeViewModel
import com.example.whattoeat.ui.theme.screens.setting.SettingScreen
import com.example.whattoeat.ui.theme.screens.setting.SettingViewModel
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeScreen
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeViewModel

//https://nameisjayant.medium.com/nested-navigation-in-jetpack-compose-597ecdc6eebb

@Composable
fun NavigationApp(application: WhatToEatApplication,
    settingViewModel: SettingViewModel,
    saveRecipeViewModel: SaveRecipeViewModel,
    randomRecipeViewModel: RandomRecipeViewModel,
    specificRecipeViewModel: SpecificRecipeViewModel
) {
    val navigationController = rememberNavController()
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }


    //https://medium.com/@itsuki.enjoy/android-kotlin-jetpack-compose-popup-with-navigation-3e48cbe6bf24
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ) {

                // Navigate to Home
                IconButton(onClick = {
                    selected.value = Icons.Default.Home
                    navigationController.navigate(Screen.Home.screen) {
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Home) Color.White else Color.DarkGray
                    )
                }

                // Navigate to SaveRecipe
                IconButton(onClick = {
                    selected.value = Icons.Default.Favorite
                    navigationController.navigate(Screen.SaveRecipe.screen) {
                        popUpTo(Screen.Home.screen)
                        navigationController.popBackStack()
                    }

                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Favorite) Color.White else Color.DarkGray
                    )
                }

                // Navigate to RandomRecipe
                IconButton(onClick = {
                    selected.value = Icons.Default.Refresh

                    navigationController.navigate(Screen.RandomRecipe.screen) {
                        popUpTo(Screen.Home.screen)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Refresh) Color.White else Color.DarkGray
                    )
                }

                // Navigate to Setting
                IconButton(onClick = {
                    selected.value = Icons.Default.Settings
                    navigationController.navigate(Screen.Setting.screen) {
                        popUpTo(Screen.Home.screen)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Settings) Color.White else Color.DarkGray
                    )
                }
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navigationController,
            startDestination = Screen.Home.screen,
            modifier = Modifier.padding((paddingValues))
        ) {
            composable(Screen.Home.screen) { HomeScreen() }
            composable(Screen.RandomRecipe.screen) {RandomRecipeScreen(application,randomRecipeViewModel = randomRecipeViewModel) }
            composable(Screen.SaveRecipe.screen) {
                SaveRecipeScreen(
                    saveRecipeViewModel = saveRecipeViewModel,
                    // TODO NOT A GOOD PRACTICE TO GIVE NAVCONTROLLER. SHOULD EXPOSE AN EVENT : https://developer.android.com/guide/navigation/use-graph/navigate?hl=fr
                    navController = navigationController
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
                SpecificRecipeScreen(application,
                    specificRecipeViewModel = specificRecipeViewModel
                )

            }

            composable(Screen.Setting.screen) { SettingScreen(settingViewModel) }
        }
    }
}
