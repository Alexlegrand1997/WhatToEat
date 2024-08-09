package com.example.whattoeat.ui.theme.screens.saveRecipe.components

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.whattoeat.core.Screen
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.ui.theme.composables.LoadImage

@Composable
fun SaveRecipeCard(saveRecipe:SaveRecipeEntity, navController: NavController) {
    Card(
        onClick = {seeSpecificRecipe(saveRecipe.idRecipe,navController)},
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


fun seeSpecificRecipe(idRecipe:Int, navController: NavController){
    navController.navigate("${Screen.SpecificRecipe.screen}/$idRecipe") {
        // TODO : Have to verify if this is necessary
        popUpTo(2)
    }


}