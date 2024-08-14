package com.example.whattoeat.ui.theme.screens.setting.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.whattoeat.ui.theme.screens.setting.SettingViewModel
import com.example.whattoeat.ui.theme.screens.setting.SettingsScreenEvent
import com.example.whattoeat.ui.theme.screens.setting.themes


@Composable
fun ThemeChoice(
    modifier: Modifier,
    optionsList: List<String>,
    defaultSelected: Int,
    settingViewModel: SettingViewModel
) {
    var selectedOption by remember {
        mutableIntStateOf(defaultSelected)
    }

    Column(
        modifier
            .fillMaxWidth(0.95f)
            .wrapContentHeight()
    ) {

        LazyRow(
            Modifier
                .border(1.dp,MaterialTheme.colorScheme.background, CircleShape)
                .clip( shape = CircleShape),

//            horizontalArrangement = Arrangement.spacedBy(4.dp),
            userScrollEnabled = false
        )
        {
            items(optionsList) {
                ButtonForDialog(
                    modifier = Modifier
                        .height(48.dp)
                        .fillParentMaxWidth(1 / 3f),
                    it,
                    optionsList[selectedOption]
                ) { selectedValue ->
                    selectedOption = optionsList.indexOf(selectedValue)
                    settingViewModel.handleScreenEvents(SettingsScreenEvent.SaveSetting(themeValue = themes[selectedOption]))

                }
            }
        }

    }
}


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
            ).
            border(1.dp,MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}



