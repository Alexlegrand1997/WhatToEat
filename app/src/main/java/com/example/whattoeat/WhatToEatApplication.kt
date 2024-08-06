package com.example.whattoeat

import android.app.Application
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import com.example.whattoeat.core.userDataStore.AppSettingsRepository
import com.example.whattoeat.core.userDataStore.AppSettingsRepositoryImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhatToEatApplication: Application() {

    var theme = mutableStateOf("")
    



}