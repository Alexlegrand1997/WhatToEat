package com.example.whattoeat.ui.theme.screens.setting

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whattoeat.R
import com.example.whattoeat.core.Constants.DEFAULT_INGREDIENT_UNIT_VALUE
import com.example.whattoeat.core.Constants.DEFAULT_THEME_VALUE
import com.example.whattoeat.ui.theme.composables.LoadingSpinner
import com.example.whattoeat.ui.theme.screens.setting.components.IngredientUnitChoice
import com.example.whattoeat.ui.theme.screens.setting.components.ThemeChoice


@Composable
fun SettingScreen(settingViewModel: SettingViewModel = hiltViewModel()) {

    // https://oguzhandogdu.medium.com/changing-the-application-theme-with-jetpack-datastore-c07c321fda79
    // https://tomas-repcik.medium.com/dependency-injection-with-hilt-in-android-development-e23fc636d65c

    // Change Theme : https://www.youtube.com/watch?v=JAMuaaJwVjw

    var settingPos by remember {
        mutableStateOf(SettingPosition(0, 0))
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
            settingPos.themePos = themes.find { it.pos == state.appSetting.theme }!!.pos
            settingPos.ingredientUnitPos = ingredientUnits.find {it.pos == state.appSetting.ingredientUnit}!!.pos
            settings(settingPos, settingViewModel)
        }
    }
}

@Composable
fun settings(settingPos: SettingPosition, settingViewModel: SettingViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 0.dp, top = 16.dp, end = 0.dp, bottom = 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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


@SuppressLint("StaticFieldLeak")
public enum class ThemeValues(val title: Int, val pos: Int) {
    LIGHT_MODE(R.string.light_mode, 0),
    DARK_MODE(R.string.dark_mode, 1),
    SYSTEM_DEFAULT(R.string.system, 2);

    fun getText(context: Context): String {
        return context.getString(this.title)
    }
}

val themes = listOf(
    ThemeValues.valueOf("LIGHT_MODE"),
    ThemeValues.valueOf("DARK_MODE"),
    ThemeValues.valueOf("SYSTEM_DEFAULT")
)

enum class IngredientUnitValues(val title: Int, val pos:Int) {

    METRIC_MODE(R.string.metric,0),
    US_MODE(R.string.us,1);

    fun getText(context: Context): String {
        return context.getString(this.title)
    }
}

val ingredientUnits = listOf(
    IngredientUnitValues.valueOf("METRIC_MODE"),
    IngredientUnitValues.valueOf("US_MODE")
)

data class SettingPosition(
    var themePos: Int = DEFAULT_THEME_VALUE,
    var ingredientUnitPos: Int = DEFAULT_INGREDIENT_UNIT_VALUE
)
