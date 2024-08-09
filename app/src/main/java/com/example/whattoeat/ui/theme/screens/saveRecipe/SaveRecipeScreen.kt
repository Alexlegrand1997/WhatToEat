package com.example.whattoeat.ui.theme.screens.saveRecipe

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.IngredientCard
import com.example.whattoeat.ui.theme.screens.saveRecipe.components.SaveRecipeCard


@Composable
fun SaveRecipeScreen(
    saveRecipeViewModel: SaveRecipeViewModel,
    navController: NavController
) {

    val saveRecipes by saveRecipeViewModel.getAllSaveRecipe.collectAsState(initial = emptyList())
    val saveRecipeUIState by saveRecipeViewModel.saveRecipeUIState.collectAsState()

//    when (val state = saveRecipeUIState){
//        is SaveRecipeUIState.Error -> Toast.makeText(
//            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
//        ).show()
//        SaveRecipeUIState.Loading -> LoadingSpinner()
//        is SaveRecipeUIState.Success -> test(state.recipes,navController)
//    }





    if (saveRecipes.isNotEmpty()){
        test(saveRecipes,navController)
    }
}


@Composable
fun test(saveRecipes: List<SaveRecipeEntity>, navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.95f)
              ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(saveRecipes) { recipe ->
                SaveRecipeCard(saveRecipe = recipe, navController)
            }
        }

    }
}