package com.example.whattoeat.ui.theme.screens.saveRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.IngredientCard
import com.example.whattoeat.ui.theme.screens.saveRecipe.components.SaveRecipeCard


@Composable
fun SaveRecipeScreen(
    saveRecipeViewModel: SaveRecipeViewModel = hiltViewModel(),
    navController: NavController
) {

    val saveRecipes by saveRecipeViewModel.getAllSaveRecipe.collectAsState(initial = emptyList())

    if (saveRecipes.isNotEmpty()) {
        SaveRecipeList(saveRecipes, navController)
    }
}


@Composable
fun SaveRecipeList(saveRecipes: List<SaveRecipeEntity>, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .padding(8.dp)
                   ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(saveRecipes) { recipe ->
                    SaveRecipeCard(saveRecipe = recipe, navController)
                }
            }
        }
    }
}