package com.example.whattoeat.ui.theme.screens.saveRecipe.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.ui.theme.composables.LoadImage

@Composable
fun SaveRecipeCard(saveRecipe:SaveRecipeEntity) {
    Card(
        Modifier
            .fillMaxSize(0.95f)
            .padding(top = 4.dp)
    ) {
        Row(modifier = Modifier.height(75.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.fillMaxWidth(0.3f), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoadImage(saveRecipe.image)
            }
            Column(Modifier.padding(start = 8.dp)) {
                Text(saveRecipe.title)
            }
        }
    }

}