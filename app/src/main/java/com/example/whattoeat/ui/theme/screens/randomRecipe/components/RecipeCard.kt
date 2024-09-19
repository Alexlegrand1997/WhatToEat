package com.example.whattoeat.ui.theme.screens.randomRecipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.whattoeat.core.Constants.KEY_RANDOM_RECIPE
import com.example.whattoeat.core.CurrentSpecificRandomRecipe
import com.example.whattoeat.core.CurrentSpecificRecipe
import com.example.whattoeat.core.CurrentSpecificSearchRecipe
import com.example.whattoeat.core.Screen
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.composables.LoadImage
import com.example.whattoeat.ui.theme.theme.Typography


@Composable
fun RecipeCard(recipe: Recipe, navController: NavController, key: String) {

    // Fix card not same height https://stackoverflow.com/questions/70325468/how-to-level-the-height-of-items-in-lazyverticalgrid
    Card(
        onClick = { seeSpecificRandomRecipe(recipe, navController, key) },
        Modifier
            .fillMaxWidth()
            .height(96.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.outline)
        ) {
            Column(
                Modifier
                    .weight(1.2f)
                    .background(MaterialTheme.colorScheme.onBackground)
            ) {
                LoadImage(
                    recipe.image,
                    recipe.title,
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxSize(),
                    contentScale = ContentScale.FillBounds
                )
            }
            Column(
                Modifier
                    .weight(2f)
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = recipe.title,
                    color = MaterialTheme.colorScheme.background,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
                )

            }
        }
    }

}

fun seeSpecificRandomRecipe(recipe: Recipe, navController: NavController, key: String) {

    if (key == KEY_RANDOM_RECIPE)
        CurrentSpecificRandomRecipe.setSpecificRandomRecipe(recipe)
    else
        CurrentSpecificSearchRecipe.setSpecificSearchRecipe(recipe)

    navController.navigate("${Screen.SpecificRandomRecipe.screen}/$key") {
        // TODO : Have to verify if this is necessary
//        popUpTo(2)
    }
}