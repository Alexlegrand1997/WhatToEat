package com.example.whattoeat.ui.theme.screens.saveRecipe.components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.Screen
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.ui.theme.composables.LoadImage
import com.example.whattoeat.ui.theme.theme.Typography

@Composable
fun SaveRecipeCard(saveRecipe:SaveRecipeEntity, navController: NavController) {
    Card(
        onClick = {seeSpecificRecipe(saveRecipe.idRecipe,navController)},
        Modifier
            .fillMaxWidth()
            .height(96.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.outline)
        ) {
            Column(Modifier.weight(1.2f).background(MaterialTheme.colorScheme.onBackground)) {
                LoadImage(saveRecipe.image, saveRecipe.title,Modifier.align(Alignment.CenterHorizontally).fillMaxSize(), contentScale = ContentScale.FillBounds)
            }
            Column(
                Modifier.weight(2f)
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = saveRecipe.title,
                    color = MaterialTheme.colorScheme.background,
                    style = Typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )

            }
        }
    }
}


fun seeSpecificRecipe(idRecipe:Int, navController: NavController){
    navController.navigate("${Screen.SpecificRecipe.screen}/$idRecipe") {
        // TODO : Have to verify if this is necessary
        popUpTo(2)
    }
}