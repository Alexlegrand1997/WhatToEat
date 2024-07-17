package com.example.whattoeat.ui.theme.screens.saveRecipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SaveRecipeScreen(navController : NavHostController = rememberNavController()) {
   Surface(modifier= Modifier.fillMaxSize()){
    Text(text = "saveRecipe")
   }
}