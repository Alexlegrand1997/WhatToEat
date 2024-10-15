package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.InstructionInfoCardModal
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeViewModel

@Composable
fun RecipeScreenCard(
    application: WhatToEatApplication,
    recipe: Recipe,
    specificRecipeViewModel: SpecificRecipeViewModel,
    navController: NavController
) {

    var currentRecipeInfo = remember {
        mutableStateOf(false)
    }


    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RecipeInfo(
                Modifier.weight(9f),
                application = application,
                recipe = recipe,
                navController
            )
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
                    Text(stringResource(R.string.instruction))
                }
                // Context use for the ROOM db
                val context = LocalContext.current
                //TODO : Have to change state of button to save or removeFromSave if the recipe is in the save list of the user
                Button(
                    onClick = { saveRecipe(specificRecipeViewModel, recipe) },
                    Modifier
                        .padding(start = 4.dp, end = 4.dp)
                        .fillMaxWidth(0.5f)
                ) {
                    if (specificRecipeViewModel.isRecipeSave)
                        Text(stringResource(R.string.Remove))
                    else
                        Text(stringResource(R.string.Save))
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
    specificRecipeViewModel: SpecificRecipeViewModel,
    recipe: Recipe
) {
    specificRecipeViewModel.saveRecipe(recipe)
}

