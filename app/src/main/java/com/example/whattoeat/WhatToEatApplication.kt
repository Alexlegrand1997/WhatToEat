package com.example.whattoeat

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.dataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhatToEatApplication: Application() {


//        var theme = mutableStateOf("")
    var theme = mutableStateOf("")
}