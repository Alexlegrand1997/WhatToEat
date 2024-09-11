package com.example.whattoeat.ui.theme.screens.randomRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whattoeat.R
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.composables.RecipeInfo
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.InstructionInfoCardModal
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.RecipeCard


@Composable
fun RandomRecipeScreen(
    application: WhatToEatApplication,
    randomRecipeViewModel: RandomRecipeViewModel,
    navController: NavController
) {
    val randomRecipeUIState by randomRecipeViewModel.randomRecipeUIState.collectAsState()

    when (val state = randomRecipeUIState) {
        is RandomRecipeUIState.Error -> Toast.makeText(
            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
        ).show()

        RandomRecipeUIState.Loading -> {
            LoadingSpinner()
        }

        is RandomRecipeUIState.Success -> {
            RandomRecipeList(
                recipes = state.recipes.recipes,
                randomRecipeViewModel = randomRecipeViewModel,
                navController
            )
        }
    }

}


@Composable
fun RandomRecipeList(
    recipes: List<Recipe>,
    randomRecipeViewModel: RandomRecipeViewModel,
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
            LazyColumn(
                modifier = Modifier
                    .padding(12.dp)
                    .weight(0.8f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                items(recipes) { recipe ->
                    RecipeCard(recipe = recipe, navController = navController)
                }


            }
            Button(
                onClick = { refreshRecipe(randomRecipeViewModel) },
                Modifier
                    .padding(horizontal = 8.dp)
                    .weight(0.05f)
                    .fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.new_recipe))
            }
            Spacer(modifier = Modifier.weight(0.01f))

        }
    }
}


private fun refreshRecipe(randomRecipeViewModel: RandomRecipeViewModel) {
    randomRecipeViewModel.getRandomRecipe()
}
