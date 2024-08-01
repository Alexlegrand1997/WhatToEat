package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.whattoeat.R
import com.example.whattoeat.core.Constants
import com.example.whattoeat.models.ExtendedIngredient

@Composable
fun LoadImageIngredient(ingredient: ExtendedIngredient) {
    SubcomposeAsyncImage(model = Constants.URL_CDN_INGREDIENT + ingredient.image,
        contentScale = ContentScale.FillHeight,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxHeight(0.8f),
        contentDescription = ingredient.name,
        filterQuality = FilterQuality.High,
        loading = {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.height(225.dp)
            ) {
                CircularProgressIndicator(modifier = Modifier.size(60.dp))
            }
        },
        error = {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = stringResource(id = R.string.picture_unavailable)
            )
        })
}
