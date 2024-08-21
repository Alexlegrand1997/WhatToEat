package com.example.whattoeat.ui.theme.screens.specificRandomRecipe

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.whattoeat.models.Recipe

@Composable
fun SpecificRandomRecipeScreen() {
    Text(text = "SpecificRandomRecipeScreen")
}

/*@Composable
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
            RecipeInfo(Modifier.weight(9f), application = application, recipe = recipes.recipes[0])
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


private fun saveRecipe(randomRecipeViewModel: RandomRecipeViewModel, recipe: Recipe) {
    // TODO: MAKE IT SO SAVE AND SAVED
    randomRecipeViewModel.saveRecipe(recipe)

}
 */