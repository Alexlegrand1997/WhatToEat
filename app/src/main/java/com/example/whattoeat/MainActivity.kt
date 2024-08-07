package com.example.whattoeat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.whattoeat.ui.theme.composables.NavigationApp
import com.example.whattoeat.ui.theme.screens.home.HomeViewModel
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeViewModel
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeViewModel
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeViewModel
import com.example.whattoeat.ui.theme.theme.WhatToEatTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var application: WhatToEatApplication
    private val homeViewModel by viewModels<HomeViewModel>()
    private val randomRecipeViewModel by viewModels<RandomRecipeViewModel>()
    private val saveRecipeViewModel by viewModels<SaveRecipeViewModel>()
    private val specificRecipeViewModel by viewModels<SpecificRecipeViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatToEatTheme(

                application.theme.value
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    NavigationApp(
                        homeViewModel,
                        saveRecipeViewModel,
                        randomRecipeViewModel,
                        specificRecipeViewModel
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NavBarPreview() {
//    WhatToEatTheme {
////        NavigationApp(saveRecipeViewModel)
//    }
}

