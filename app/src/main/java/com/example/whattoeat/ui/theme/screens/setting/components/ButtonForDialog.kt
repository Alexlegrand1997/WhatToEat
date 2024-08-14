package com.example.whattoeat.ui.theme.screens.setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonForDialog(
    modifier: Modifier,
    text: String,
    selectedValue: String,
    onClickListener: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .selectable(selected = (text == selectedValue), onClick = {
                onClickListener(text)
            })
            .background(
                if (text == selectedValue) MaterialTheme.colorScheme.primaryContainer else
                    MaterialTheme.colorScheme.outline
            )
            .border(1.dp, MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}