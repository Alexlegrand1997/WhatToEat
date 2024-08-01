package com.example.whattoeat.ui.theme.screens.saveRecipe

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.IngredientCard
import com.example.whattoeat.ui.theme.screens.saveRecipe.components.SaveRecipeCard


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
fun test(saveRecipes: List<SaveRecipeEntity>) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.95f)
              ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(saveRecipes) { recipe ->
                SaveRecipeCard(saveRecipe = recipe)
            }
        }

    }
}