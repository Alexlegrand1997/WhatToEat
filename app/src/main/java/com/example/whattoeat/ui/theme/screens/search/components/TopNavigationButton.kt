package com.example.whattoeat.ui.theme.screens.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.whattoeat.R

@Composable
fun TopNavigationButton(
    text: String,
    modifier: Modifier = Modifier,
    leftScreenValue: Boolean,
    onMutableValueChange: (Boolean) -> Unit
) {

    Text(
        text = text,
        modifier
            .clickable
            { onMutableValueChange(true) }
            .fillMaxHeight()
            .background(
                if (leftScreenValue) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.outline
                }
            )
            .wrapContentHeight(align = Alignment.CenterVertically),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge
    )
}
