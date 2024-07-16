package com.example.whattoeat.ui.theme.screens.randomRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.ui.theme.composables.LoadingSpinner

@Composable
fun RandomRecipeScreen(navController: NavHostController= rememberNavController(), randomRecipeViewModel: RandomRecipeViewModel= viewModel()) {
    val randomRecipeUIState by randomRecipeViewModel.randomRecipeUIState.collectAsState()

    when(val state =randomRecipeUIState){
        is RandomRecipeUIState.Error -> Toast.makeText(
            LocalContext.current,
            state.exception.message,
            Toast.LENGTH_LONG
        ).show()

        RandomRecipeUIState.Loading -> LoadingSpinner()
        is RandomRecipeUIState.Success ->{
            RandomRecipeScreenCard(state.recipes, randomRecipeViewModel)
        }
    }

}

@Composable
fun RandomRecipeScreenCard(recipes: Recipes, randomRecipeViewModel: RandomRecipeViewModel){
    Surface(modifier= Modifier.fillMaxSize()){
        Text(text = recipes.recipes[0].title)
    }
}



