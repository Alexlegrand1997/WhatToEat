package com.example.whattoeat.ui.theme.screens.saveRecipe

import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.whattoeat.data.entities.RecipeSaveEntity


@Composable
fun SaveRecipeScreen(
    saveRecipeViewModel: SaveRecipeViewModel,
    onUpdate: (id: Int) -> Unit
) {

    val saveRecipes by saveRecipeViewModel.getAllSaveRecipe.collectAsState(initial = emptyList())

    if (saveRecipes.isNotEmpty()){
        test(saveRecipes)
    }
}


@Composable
fun test(saveRecipes: List<RecipeSaveEntity>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = saveRecipes[0].title.toString())
    }
}