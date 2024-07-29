package com.example.whattoeat.ui.theme.screens.saveRecipe

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.whattoeat.data.databases.SaveRecipeDatabase
import com.example.whattoeat.data.repositories.SaveRecipeRepository

@Composable
fun SaveRecipeScreen(navController : NavHostController = rememberNavController(),

) {



}



@Composable
fun test(viewModel: SaveRecipeViewModel){
    Surface(modifier= Modifier.fillMaxSize()){
        Text(text = "saveRecipe")
    }
}