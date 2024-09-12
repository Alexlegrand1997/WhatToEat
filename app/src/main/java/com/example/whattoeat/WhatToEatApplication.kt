package com.example.whattoeat

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.whattoeat.core.Constants.DEFAULT_INGREDIENT_UNIT_VALUE
import com.example.whattoeat.core.Constants.DEFAULT_THEME_VALUE
import com.example.whattoeat.data.repositories.AppSetting
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhatToEatApplication: Application() {
    var appSetting : MutableState<AppSetting> = mutableStateOf(AppSetting(theme = DEFAULT_THEME_VALUE, ingredientUnit = DEFAULT_INGREDIENT_UNIT_VALUE, username="", pointLeft = 0.0))
}