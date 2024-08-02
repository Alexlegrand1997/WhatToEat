package com.example.whattoeat.ui.theme.screens.specificRecipe

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.composables.LoadingSpinner

@Composable
fun SpecificRecipeScreen(specificRecipeViewModel: SpecificRecipeViewModel, idRecipe: String?) {

    val specificRecipeUIState by specificRecipeViewModel.specificRecipeUIState.collectAsState()

// TODO : FIX the init of viewModel that never launch
    when (val state = specificRecipeUIState) {
        is SpecificRecipeUIState.Error -> Toast.makeText(
            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
        ).show()

        SpecificRecipeUIState.Loading -> LoadingSpinner()
        is SpecificRecipeUIState.Success -> {
            SpecificRecipeScreenCard(state.recipe)
        }
    }

}

@Composable
fun SpecificRecipeScreenCard(recipe: Recipe) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = recipe.title)
    }
}