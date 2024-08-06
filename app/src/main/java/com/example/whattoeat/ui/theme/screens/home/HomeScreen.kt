package com.example.whattoeat.ui.theme.screens.home

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.whattoeat.R
import com.example.whattoeat.ui.theme.screens.home.components.SingleSelectDialog
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    var themeLocation by remember {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()
    val settingsUiState by homeViewModel.settingsState.collectAsState()


    Text(text = "Home")
    Button(onClick = {
        homeViewModel.handleScreenEvents(SettingsScreenEvent.OpenThemeDialog(true))}) {
        Text("Theme")
    }
    LaunchedEffect(settingsUiState.getThemeValue) {
        homeViewModel.handleScreenEvents(SettingsScreenEvent.ThemeChanged)
        themeLocation = if (settingsUiState.getThemeValue.isNullOrEmpty()) {
            2
        } else {
            themes.indexOf(settingsUiState.getThemeValue)
        }
    }
    if (settingsUiState.openThemeDialog) {

        SingleSelectDialog(modifier = Modifier,
            title = stringResource(id = R.string.choise_theme),
            optionsList = themes,
            defaultSelected = themeLocation,
            submitButtonText = stringResource(id = R.string.ok),
            dismissButtonText = stringResource(id = R.string.cancel),
            onSubmitButtonClick = { id ->
                coroutineScope.launch {
                    homeViewModel.handleScreenEvents(SettingsScreenEvent.SetNewTheme(themes[id]))
                    homeViewModel.handleScreenEvents(SettingsScreenEvent.ThemeChanged)
                }
            },
            onDismissRequest = { value ->
                coroutineScope.launch {
                    homeViewModel.handleScreenEvents(SettingsScreenEvent.OpenThemeDialog(value))

                }
            })


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