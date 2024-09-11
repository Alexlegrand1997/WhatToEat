package com.example.whattoeat.ui.theme.screens.randomRecipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.models.ExtendedIngredient
import com.example.whattoeat.ui.theme.composables.LoadImageIngredient
import com.example.whattoeat.ui.theme.screens.setting.IngredientUnitValues
import com.example.whattoeat.ui.theme.theme.Typography


@Composable
fun IngredientCard(application: WhatToEatApplication, ingredient: ExtendedIngredient) {

    Card(
        Modifier
            .fillMaxWidth()
            .height(96.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.outline),
        ) {
            Column(
                Modifier
                    .weight(0.85f)
                    .background(MaterialTheme.colorScheme.onBackground),
            ) {
                LoadImageIngredient(ingredient,
                    modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxSize(),
                    contentScale = ContentScale.FillBounds)
            }

            Column(
                Modifier
                    .padding(8.dp)
                    .weight(2f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = ingredient.name, color = MaterialTheme.colorScheme.background,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold,)
                // True is US
                if (application.appSetting.value.ingredientUnit ==
                    IngredientUnitValues.US_MODE.title
                ) {
                    Text(
                        "${ingredient.measures.us.amount} ${ingredient.measures.us.unitLong}",
                        color = MaterialTheme.colorScheme.background,
                        style = Typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
                // False is Metric
                else {
                    Text(
                        "${ingredient.measures.metric.amount} ${ingredient.measures.metric.unitLong}",
                        color = MaterialTheme.colorScheme.background,
                        style = Typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                    )

                }
            }
        }
    }
}