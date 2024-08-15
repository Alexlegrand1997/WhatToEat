package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.IngredientCard

@Composable
fun RecipeInfo(
    modifier: Modifier,
    application: WhatToEatApplication,
    recipe: Recipe
) {
    var currentRecipeInfo = remember {
        mutableStateOf(false)
    }

    Column(
        modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(recipe.extendedIngredients) { ingredient ->
                IngredientCard(application, ingredient)
            }
        }
    }
}

