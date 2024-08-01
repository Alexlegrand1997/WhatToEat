package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whattoeat.core.Screen
import com.example.whattoeat.ui.theme.screens.home.HomeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeViewModel
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeScreen
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeViewModel

//https://nameisjayant.medium.com/nested-navigation-in-jetpack-compose-597ecdc6eebb

@Composable
fun NavigationApp(saveRecipeViewModel:SaveRecipeViewModel, randomRecipeViewModel: RandomRecipeViewModel) {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {

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
                        popUpTo(0)
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
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(
                        Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if (selected.value == Icons.Default.Refresh) Color.White else Color.DarkGray
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
            composable(Screen.RandomRecipe.screen) { RandomRecipeScreen(randomRecipeViewModel =randomRecipeViewModel) }
            composable(Screen.SaveRecipe.screen) {
                SaveRecipeScreen(saveRecipeViewModel = saveRecipeViewModel,
                    onUpdate = { id ->
                        navigationController.navigate(
                            route = "${Screen.SaveRecipe.screen}/$id"
                        )
                    }
                )
            }
        }
    }
}
