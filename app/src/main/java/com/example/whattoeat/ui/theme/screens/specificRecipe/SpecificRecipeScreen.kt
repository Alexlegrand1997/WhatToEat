package com.example.whattoeat.ui.theme.screens.specificRecipe

import android.widget.Toast
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
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.composables.RecipeInfo
import com.example.whattoeat.ui.theme.composables.RecipeScreenCard
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.InstructionInfoCardModal

@Composable
fun SpecificRecipeScreen(
    application: WhatToEatApplication,
    specificRecipeViewModel: SpecificRecipeViewModel,
    navController: NavController
) {

    val specificRecipeUIState by specificRecipeViewModel.specificRecipeUIState.collectAsState()

    when (val state = specificRecipeUIState) {
        is SpecificRecipeUIState.Error -> Toast.makeText(
            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
        ).show()

        SpecificRecipeUIState.Loading -> LoadingSpinner()
        is SpecificRecipeUIState.Success -> {
            specificRecipeViewModel.isSaveRecipe(state.recipe)
            RecipeScreenCard(
                application, state.recipe, specificRecipeViewModel,
                navController = navController
            )
        }
    }
}
