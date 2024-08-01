package com.example.whattoeat.ui.theme.screens.randomRecipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whattoeat.models.ExtendedIngredient
import com.example.whattoeat.ui.theme.composables.LoadImageIngredient


@Composable
fun IngredientCard(ingredient: ExtendedIngredient, typeMeasure: Int) {
    Card(
        Modifier
            .fillMaxWidth(0.95f)
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.height(75.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.fillMaxWidth(0.3f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadImageIngredient(ingredient)

            }

            Column(Modifier.padding(start = 8.dp)) {
                Text(ingredient.name)
                // True is US
                if (typeMeasure == 1) {
                    Text("${ingredient.measures.us.amount} ${ingredient.measures.us.unitLong}")
                }
                // False is Metric
                else {
                    Text("${ingredient.measures.metric.amount} ${ingredient.measures.metric.unitLong}")

                }
            }
        }
    }
}