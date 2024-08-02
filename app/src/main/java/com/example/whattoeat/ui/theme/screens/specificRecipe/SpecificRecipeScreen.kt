package com.example.whattoeat.ui.theme.screens.specificRecipe

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.composables.LoadImage
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.IngredientCard
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.InstructionInfoCardModal
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.SwitchIngredientUnitQuantity

@Composable
fun SpecificRecipeScreen(specificRecipeViewModel: SpecificRecipeViewModel) {

    val specificRecipeUIState by specificRecipeViewModel.specificRecipeUIState.collectAsState()

    when (val state = specificRecipeUIState) {
        is SpecificRecipeUIState.Error -> Toast.makeText(
            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
        ).show()

        SpecificRecipeUIState.Loading -> LoadingSpinner()
        is SpecificRecipeUIState.Success -> {
            SpecificRecipeScreenCard(state.recipe, specificRecipeViewModel)
        }
    }
}

@Composable
fun SpecificRecipeScreenCard(recipe: Recipe, specificRecipeViewModel: SpecificRecipeViewModel) {
    var currentRecipeInfo = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.3f)
            ) {
                LoadImage(
                    url = recipe.image,
                    recipe.title,
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(text = recipe.title)
            }

            val items = remember {
                listOf("Metric", "US")
            }
            var selectedIndex by remember {
                mutableStateOf(0)
            }

            SwitchIngredientUnitQuantity(
                selectedIndex = selectedIndex,
                items = items,
                onSelectionChange = {
                    selectedIndex = it
                })

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(recipe.extendedIngredients) { ingredient ->
                    IngredientCard(ingredient, selectedIndex)
                }
            }


            Row(verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center) {
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
                    onClick = { saveRecipe(specificRecipeViewModel, recipe) },
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


private fun saveRecipe(specificRecipeViewModel: SpecificRecipeViewModel, recipe: Recipe) {
    specificRecipeViewModel.saveRecipe(recipe)
}