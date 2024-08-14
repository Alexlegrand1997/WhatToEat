package com.example.whattoeat

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.whattoeat.data.repositories.AppSetting
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhatToEatApplication: Application() {

    var appSetting : MutableState<AppSetting> = mutableStateOf(AppSetting(theme = "", ingredientUnit = ""))
}