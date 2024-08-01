package com.example.whattoeat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

import androidx.compose.foundation.layout.fillMaxSize
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

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whattoeat.ui.theme.composables.NavigationApp
import com.example.whattoeat.ui.theme.screens.home.HomeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeScreen
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeViewModel
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeScreen
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeViewModel
import com.example.whattoeat.ui.theme.theme.WhatToEatTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val randomRecipeViewModel by viewModels<RandomRecipeViewModel>()
    private val saveRecipeViewModel by viewModels<SaveRecipeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatToEatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    NavigationApp(saveRecipeViewModel,randomRecipeViewModel)
                }
            }
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
    WhatToEatTheme {
//        NavigationApp(saveRecipeViewModel)
    }
}

