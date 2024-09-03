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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.setting.components.IngredientUnitChoice
import com.example.whattoeat.ui.theme.screens.setting.components.ThemeChoice


@Composable
fun SettingScreen(settingViewModel: SettingViewModel = hiltViewModel()) {

    // https://oguzhandogdu.medium.com/changing-the-application-theme-with-jetpack-datastore-c07c321fda79
    // https://tomas-repcik.medium.com/dependency-injection-with-hilt-in-android-development-e23fc636d65c

    // Change Theme : https://www.youtube.com/watch?v=JAMuaaJwVjw

    var settingPos by remember {
        mutableStateOf(SettingPosition(0,0))
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
            settingPos.themePos = if (state.appSetting.theme.isNullOrEmpty()) {
                2
            } else {
                themes.indexOf(state.appSetting.theme)
            }
            settingPos.ingredientUnitPos=if (state.appSetting.ingredientUnit.isNullOrEmpty()){
                0
            } else {
                ingredientUnits.indexOf(state.appSetting.ingredientUnit)
            }
            settings(settingPos,settingViewModel)
        }
    }
}

@Composable
fun settings(settingPos:SettingPosition,settingViewModel: SettingViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            ThemeChoice(
                modifier = Modifier,
                optionsList = themes,
                defaultSelected = settingPos.themePos,
                settingViewModel
            )

            IngredientUnitChoice(
                modifier = Modifier,
                optionsList = ingredientUnits,
                defaultSelected = settingPos.ingredientUnitPos,
                settingViewModel = settingViewModel
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

enum class IngredientUnitValues(val title: String) {
    METRIC_MODE("Metric"),
    US_MODE("US")
}

val ingredientUnits = listOf(
    IngredientUnitValues.METRIC_MODE.title,
    IngredientUnitValues.US_MODE.title
)

data class SettingPosition(
    var themePos:Int =0,
    var ingredientUnitPos:Int=0
)
