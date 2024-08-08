package com.example.whattoeat.ui.theme.screens.setting

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.whattoeat.ui.theme.composables.LoadingSpinner

@Composable
fun SettingScreen(settingViewModel: SettingViewModel) {

    // https://oguzhandogdu.medium.com/changing-the-application-theme-with-jetpack-datastore-c07c321fda79
    // https://tomas-repcik.medium.com/dependency-injection-with-hilt-in-android-development-e23fc636d65c
    var themeLocation by remember {
        mutableIntStateOf(0)
    }
    val homeUIState by settingViewModel.homeUIState.collectAsState()

    when (val state = homeUIState) {
        is SettingUIState.Error -> Toast.makeText(
            LocalContext.current, state.exception.message, Toast.LENGTH_LONG
        ).show()

        SettingUIState.Loading -> {
            LoadingSpinner()
        }

        is SettingUIState.Success -> {

            themeLocation = if (state.themeValue.isNullOrEmpty()) {
                2
            } else {
                themes.indexOf(state.themeValue)
            }
            Surface(modifier = Modifier.fillMaxSize()) {
                themeChoice(
                    modifier = Modifier,
                    optionsList = themes,
                    defaultSelected = themeLocation,
                    settingViewModel
                )

            }
        }
    }


}


@Composable
fun themeChoice(
    modifier: Modifier,
    optionsList: List<String>,
    defaultSelected: Int,
    settingViewModel: SettingViewModel
) {
    var selectedOption by remember {
        mutableIntStateOf(defaultSelected)
    }
    LazyRow(
        modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        items(optionsList) {
            if (it == optionsList[selectedOption]) {
//                homeViewModel.handleScreenEvents(SettingsScreenEvent.SetNewTheme(themes[selectedOption]))
            }
            ButtonForDialog(
                modifier = modifier, it, optionsList[selectedOption]
            ) { selectedValue ->
                selectedOption = optionsList.indexOf(selectedValue)
                settingViewModel.handleScreenEvents(SettingsScreenEvent.SetNewTheme(themes[selectedOption]))
            }

        }
    }
}

@Composable
fun ButtonForDialog(
    modifier: Modifier,
    text: String,
    selectedValue: String,
    onClickListener: (String) -> Unit
) {

    Row(
        modifier
            .fillMaxWidth()
            .selectable(selected = (text == selectedValue), onClick = {
                onClickListener(text)
            }),
        verticalAlignment = Alignment.CenterVertically,

        ) {

        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = modifier.padding(start = 2.dp),
            color = if (text == selectedValue) Color.Red else Color.Black
        )
    }
}


enum class ThemeValues(val title: String) {
    LIGHT_MODE("Light Mode"),
    DARK_MODE("Dark Mode"),
    SYSTEM_DEFAULT("System Default")
}

val themes = listOf(
    ThemeValues.LIGHT_MODE.title,
    ThemeValues.DARK_MODE.title,
    ThemeValues.SYSTEM_DEFAULT.title
)