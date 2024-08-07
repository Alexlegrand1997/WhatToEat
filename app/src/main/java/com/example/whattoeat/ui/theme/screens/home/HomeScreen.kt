package com.example.whattoeat.ui.theme.screens.home

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    // https://oguzhandogdu.medium.com/changing-the-application-theme-with-jetpack-datastore-c07c321fda79
    // https://tomas-repcik.medium.com/dependency-injection-with-hilt-in-android-development-e23fc636d65c
    var themeLocation by remember {
        mutableIntStateOf(0)
    }
    val settingsUiState by homeViewModel.settingsState.collectAsState()


    LaunchedEffect(settingsUiState.getThemeValue) {
        homeViewModel.handleScreenEvents(SettingsScreenEvent.ThemeChanged)
        themeLocation = if (settingsUiState.getThemeValue.isNullOrEmpty()) {
            2
        } else {
            themes.indexOf(settingsUiState.getThemeValue)
        }
    }
/*    Button(onClick = {
//        homeViewModel.handleScreenEvents(SettingsScreenEvent.OpenThemeDialog(true))
//    }) {
//        Text("Theme")
//    }
//    if (settingsUiState.openThemeDialog) {
//
//        SingleSelectDialog(modifier = Modifier,
//            title = stringResource(id = R.string.choise_theme),
//            optionsList = themes,
//            defaultSelected = themeLocation,
//            submitButtonText = stringResource(id = R.string.ok),
//            dismissButtonText = stringResource(id = R.string.cancel),
//            onSubmitButtonClick = { id ->
//                coroutineScope.launch {
//                    homeViewModel.handleScreenEvents(SettingsScreenEvent.SetNewTheme(themes[id]))
//                    homeViewModel.handleScreenEvents(SettingsScreenEvent.ThemeChanged)
//                }
//            },
//            onDismissRequest = { value ->
//                coroutineScope.launch {
//                    homeViewModel.handleScreenEvents(SettingsScreenEvent.OpenThemeDialog(value))
//
//                }
//            })
//    }
*/
    Surface(modifier = Modifier.fillMaxSize()) {
        themeChoice(
            modifier = Modifier,
            optionsList = themes,
            defaultSelected = themeLocation,
            homeViewModel
        )

    }


}


@Composable
fun themeChoice(
    modifier: Modifier,
    optionsList: List<String>,
    defaultSelected: Int,
    homeViewModel: HomeViewModel
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
            if(it == optionsList[selectedOption]){
//                homeViewModel.handleScreenEvents(SettingsScreenEvent.SetNewTheme(themes[selectedOption]))
            }
            ButtonForDialog(
                modifier = modifier, it, optionsList[selectedOption]
            ) { selectedValue ->
                selectedOption = optionsList.indexOf(selectedValue)
                homeViewModel.handleScreenEvents(SettingsScreenEvent.SetNewTheme(themes[selectedOption]))
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
            color = if (text==selectedValue) Color.Red else Color.Black
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