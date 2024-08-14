package com.example.whattoeat.ui.theme.screens.randomRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.ui.theme.composables.LoadImage
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.composables.RecipeInfo
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.IngredientCard
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.InstructionInfoCardModal
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.SwitchIngredientUnitQuantity


@Composable
fun RandomRecipeScreen(
    application: WhatToEatApplication,
    randomRecipeViewModel: RandomRecipeViewModel
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
            RandomRecipeScreenCard(application, state.recipes, randomRecipeViewModel)
        }
    }

}

@Composable
fun RandomRecipeScreenCard(
    application: WhatToEatApplication,
    recipes: Recipes,
    randomRecipeViewModel: RandomRecipeViewModel
) {
    var currentRecipeInfo = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RecipeInfo(Modifier.weight(9f),application = application, recipe = recipes.recipes[0])
            Row(
                modifier = Modifier.weight(0.75f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { currentRecipeInfo.value = true },
                    Modifier
                        .padding(start = 4.dp)
                        .fillMaxWidth(1 / 3f)

                ) {
                    Text("Instruction")
                }
                // Context use for the ROOM db
                val context = LocalContext.current
                //TODO : Have to change state of button to save or removeFromSave if the recipe is in the save list of the user
                Button(
                    onClick = { saveRecipe(randomRecipeViewModel, recipes.recipes[0]) },
                    Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    Text("Save")
                }
                Button(
                    onClick = { refreshRecipe(randomRecipeViewModel) },
                    Modifier
                        .padding(end = 4.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "New Recipe")
                }
            }



            if (currentRecipeInfo.value) {
                InstructionInfoCardModal(
                    onDismissRequest = {},
                    recipes.recipes[0].analyzedInstructions[0].steps,
                    currentRecipeInfo
                )
            }
        }
    }
}

private fun refreshRecipe(randomRecipeViewModel: RandomRecipeViewModel) {
    randomRecipeViewModel.getRandomRecipe()
}

private fun saveRecipe(randomRecipeViewModel: RandomRecipeViewModel, recipe: Recipe) {
    // TODO: MAKE IT SO SAVE AND SAVED
    randomRecipeViewModel.saveRecipe(recipe)

}
