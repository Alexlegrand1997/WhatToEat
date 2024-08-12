package com.example.whattoeat.ui.theme.screens.setting

import android.widget.Toast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.setting.components.themeChoice

@Composable
fun SettingScreen(settingViewModel: SettingViewModel) {

    // https://oguzhandogdu.medium.com/changing-the-application-theme-with-jetpack-datastore-c07c321fda79
    // https://tomas-repcik.medium.com/dependency-injection-with-hilt-in-android-development-e23fc636d65c

    // Change Theme : https://www.youtube.com/watch?v=JAMuaaJwVjw
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
            settings(themeLocation,settingViewModel)
        }
    }
}

@Composable
fun settings(themeLocation:Int,settingViewModel: SettingViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxWidth().padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            themeChoice(
                modifier = Modifier,
                optionsList = themes,
                defaultSelected = themeLocation,
                settingViewModel
            )

        }

    }
}


enum class ThemeValues(val title: String) {
    LIGHT_MODE("Light Mode"),
    DARK_MODE("Dark Mode"),
    SYSTEM_DEFAULT("System")
}

val themes = listOf(
    ThemeValues.LIGHT_MODE.title,
    ThemeValues.DARK_MODE.title,
    ThemeValues.SYSTEM_DEFAULT.title
)