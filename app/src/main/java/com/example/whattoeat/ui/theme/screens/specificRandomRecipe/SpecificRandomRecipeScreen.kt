package com.example.whattoeat.ui.theme.screens.specificRandomRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.CurrentSpecificRandomRecipe
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.composables.RecipeInfo
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.InstructionInfoCardModal
import kotlinx.coroutines.flow.first

@Composable
fun SpecificRandomRecipeScreen(
    application: WhatToEatApplication,
    specificRandomRecipeViewModel: SpecificRandomRecipeViewModel = hiltViewModel()
) {
    val recipe: Recipe = CurrentSpecificRandomRecipe.getSpecificRandomRecipe()

    RandomRecipeScreenCard(
        application = application,
        recipe = recipe,
        specificRandomRecipeViewModel = specificRandomRecipeViewModel
    )
}

@Composable
fun RandomRecipeScreenCard(
    application: WhatToEatApplication,
    recipe: Recipe,
    specificRandomRecipeViewModel: SpecificRandomRecipeViewModel
) {
    var currentRecipeInfo = remember {
        mutableStateOf(false)
    }

    var isSaveRecipe = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RecipeInfo(Modifier.weight(9f), application = application, recipe = recipe)
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
                    onClick = { saveRecipe(specificRandomRecipeViewModel, recipe) },
                    Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .fillMaxWidth(0.5f)
                ) {

                    Text("Save")

                }
            }

            if (currentRecipeInfo.value) {
                InstructionInfoCardModal(
                    onDismissRequest = {},
                    recipe.analyzedInstructions[0].steps,
                    currentRecipeInfo
                )
            }
        }
    }
}


private fun saveRecipe(
    specificRandomRecipeViewModel: SpecificRandomRecipeViewModel,
    recipe: Recipe
) {
    // TODO: MAKE IT SO SAVE AND SAVED
    specificRandomRecipeViewModel.saveRecipe(recipe)
}

private suspend fun isSaveRecipe(
    specificRandomRecipeViewModel: SpecificRandomRecipeViewModel,
    recipe: Recipe
): Boolean {
    return specificRandomRecipeViewModel.isSaveRecipe(recipe)

}
