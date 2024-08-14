package com.example.whattoeat.ui.theme.screens.setting.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.whattoeat.ui.theme.screens.setting.SettingViewModel
import com.example.whattoeat.ui.theme.screens.setting.SettingsScreenEvent
import com.example.whattoeat.ui.theme.screens.setting.ingredientUnits
import com.example.whattoeat.ui.theme.screens.setting.themes

@Composable
fun IngredientUnitChoice(
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
                .border(1.dp, MaterialTheme.colorScheme.background, CircleShape)
                .clip(shape = CircleShape),

            userScrollEnabled = false
        )
        {
            items(optionsList) {
                ButtonForDialog(
                    modifier = Modifier
                        .height(48.dp)
                        .fillParentMaxWidth(1 / 2f),
                    it,
                    optionsList[selectedOption]
                ) { selectedValue ->
                    selectedOption = optionsList.indexOf(selectedValue)
                    settingViewModel.handleScreenEvents(
                        SettingsScreenEvent.SaveSetting(
                            ingredientUnitValue = ingredientUnits[selectedOption]
                        )
                    )
                }
            }
        }

    }
}


