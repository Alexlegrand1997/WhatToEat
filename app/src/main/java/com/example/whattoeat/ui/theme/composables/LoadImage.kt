package com.example.whattoeat.ui.theme.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.whattoeat.R

@Composable
fun LoadImage(url: String, title: String = "", modifier: Modifier = Modifier, contentScale: ContentScale =ContentScale.Fit) {
    SubcomposeAsyncImage(
        model = url,
        contentScale = contentScale,
        modifier = modifier,
        contentDescription = title,
        filterQuality = FilterQuality.High,
        loading = {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier.fillMaxHeight()
            ) {
                CircularProgressIndicator(modifier = Modifier.size(60.dp))
            }
        },
        error = {
            // A picture for recipe from spoonacular is 556px X 370px
            val pxValue = with(LocalDensity.current) { 556.toDp() }
            val pyValue = with(LocalDensity.current) { 370.toDp() }
            Image(
                modifier = modifier,
                contentScale = ContentScale.Fit,
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = stringResource(id = R.string.picture_unavailable),

            )

        })
}