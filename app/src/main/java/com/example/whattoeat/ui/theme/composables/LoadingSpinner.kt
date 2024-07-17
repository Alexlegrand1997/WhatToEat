package com.example.whattoeat.ui.theme.composables


import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.LinearProgressIndicator

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.sp

import kotlin.random.Random

@Preview
@Composable
fun LoadingSpinner() {
    Surface(modifier = Modifier.fillMaxSize()) {
        val imgNumber = Random.nextInt(1, 3)
        RandomLoadingImg(imgNumber.toString())
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.5f))

            Text(
                text = "Andromia",
                style = TextStyle(fontSize = 65.sp, fontFamily = FontFamily.Monospace, color = Color.White, fontWeight = FontWeight.SemiBold)
            )

            LinearProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}