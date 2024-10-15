package com.example.whattoeat.ui.theme.screens.specificRandomRecipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.Constants.KEY_RANDOM_RECIPE
import com.example.whattoeat.core.CurrentSpecificRandomRecipe
import com.example.whattoeat.core.CurrentSpecificSearchRecipe
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.composables.RecipeScreenCard
import com.example.whattoeat.ui.theme.screens.home.HomeViewModel
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeViewModel

@Composable
fun SpecificRandomRecipeScreen(
    key: String,
    application: WhatToEatApplication,
    specificRecipeViewModel: SpecificRecipeViewModel = hiltViewModel(),
    navController: NavController
) {

    var recipe: Recipe = if (key == KEY_RANDOM_RECIPE)
        CurrentSpecificRandomRecipe.getSpecificRandomRecipe()
    else
        CurrentSpecificSearchRecipe.getSpecificSearchRecipe()

    LaunchedEffect(recipe) {
        if (recipe.id != 0) {
            isSaveRecipe(specificRecipeViewModel, recipe)
        }
    }
    RecipeScreenCard(
        application = application,
        recipe = recipe,
        specificRecipeViewModel = specificRecipeViewModel,
        navController = navController
    )
}

private fun isSaveRecipe(
    specificRecipeViewModel: SpecificRecipeViewModel,
    recipe: Recipe
) {
    specificRecipeViewModel.isSaveRecipe(recipe)
}
