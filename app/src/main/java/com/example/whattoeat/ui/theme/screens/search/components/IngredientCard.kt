package com.example.whattoeat.ui.theme.screens.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.whattoeat.R
import com.example.whattoeat.models.IngredientSearch
import com.example.whattoeat.ui.theme.composables.LoadImageIngredient
import com.example.whattoeat.ui.theme.theme.Typography

@Composable
fun IngredientCard(
    listIncludeIngredient: SnapshotStateList<IngredientSearch>,
    ingredient: IngredientSearch
) {

    Card(
        Modifier
            .fillMaxSize()
            .padding(8.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(48.dp),
            verticalAlignment = Alignment.CenterVertically) {
            LoadImageIngredient(
                ingredientName = ingredient.name,
                ingredientImage = ingredient.image,
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .padding(0.dp,0.dp,0.dp,0.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = ingredient.name,
                Modifier
                    .padding(8.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth(0.70f)
                , overflow = TextOverflow.Ellipsis,
                style = Typography.titleMedium
            )
            Icon(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable {
                        listIncludeIngredient.removeAt(
                            listIncludeIngredient.indexOf(
                                ingredient
                            )
                        )
                    },
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Remove ${ingredient.name}"
            )
        }
    }
}