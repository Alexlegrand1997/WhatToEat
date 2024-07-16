package com.example.whattoeat.ui.theme.screens.randomRecipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun RandomRecipeScreen(navController: NavHostController= rememberNavController()) {
  Surface(modifier= Modifier.fillMaxSize()){
    Text(text = "RandomRecipe")

  }

}

@Composable
fun RandomRecipeVisual(navController: NavHostController){
    Text(text = "RandomRecipe")
}