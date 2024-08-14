package com.example.whattoeat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.whattoeat.ui.theme.composables.NavigationApp
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeViewModel
import com.example.whattoeat.ui.theme.screens.saveRecipe.SaveRecipeViewModel
import com.example.whattoeat.ui.theme.screens.setting.SettingViewModel
import com.example.whattoeat.ui.theme.screens.setting.SettingsScreenEvent
import com.example.whattoeat.ui.theme.screens.specificRecipe.SpecificRecipeViewModel
import com.example.whattoeat.ui.theme.theme.WhatToEatTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var application: WhatToEatApplication
    private val settingViewModel by viewModels<SettingViewModel>()
    private val randomRecipeViewModel by viewModels<RandomRecipeViewModel>()
    private val saveRecipeViewModel by viewModels<SaveRecipeViewModel>()
    private val specificRecipeViewModel by viewModels<SpecificRecipeViewModel>()


    // TODO : When theme of phone is change the nav bar is not set to the correct page when go back in app
    // TODO : When open app for first time the theme load first is the phone theme and after the app theme is load. It will be visible when phone is in dark mode and app in light mode
    // TRY THIS : https://www.youtube.com/watch?v=JAMuaaJwVjw

    // TODO : When a specificRecipe is load and we check another one we can briefly see the old recipe

    // TODO : ROUND the quantity of ingredient to 0.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO : See if this is a good practice to load setting before app launch
        // This is use to load the theme in datastore before the app launch so we don't see the cellphone system
        // theme load before the chosen theme of the app
        runBlocking {
                settingViewModel.handleScreenEvents(SettingsScreenEvent.GetSetting)
        }
        setContent {
            WhatToEatTheme(
                application.appSetting.value.theme
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                )
                {
                    NavigationApp(application,
                        settingViewModel,
                        saveRecipeViewModel,
                        randomRecipeViewModel,
                        specificRecipeViewModel
                    )
                }
            }
        }

    }
}

@Preview
@Composable
fun NavBarPreview() {
//    WhatToEatTheme {
////        NavigationApp(saveRecipeViewModel)
//    }
}

