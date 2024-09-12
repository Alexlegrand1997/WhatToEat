package com.example.whattoeat.ui.theme.composables

import androidx.annotation.FloatRange
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whattoeat.R
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.screens.randomRecipe.components.IngredientCard
import com.example.whattoeat.ui.theme.theme.Typography

@Composable
fun RecipeInfo(
    modifier: Modifier,
    application: WhatToEatApplication,
    recipe: Recipe,
    navController: NavController,
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
            Box(
                Modifier.fillMaxWidth(),
            ) {

                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "${R.string.back}",
                    modifier = Modifier.clickable { navController.popBackStack() }.align(Alignment.TopStart))

                LoadImage(
                    url = recipe.image,
                    recipe.title,
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .align(Alignment.Center)

                )

            }
        }

        Text(
            text = recipe.title,
            style = Typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 4.dp)
                .fillMaxHeight(0.12f),
            maxLines = 2,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(recipe.extendedIngredients) { ingredient ->
                IngredientCard(application, ingredient)
            }
        }
    }
}

