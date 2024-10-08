package com.example.whattoeat.ui.theme.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.Constants.DEFAULT_INGREDIENT_UNIT_VALUE
import com.example.whattoeat.core.Constants.DEFAULT_THEME_VALUE
import com.example.whattoeat.core.DataStoreResult
import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.data.repositories.AppSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val dataStore: AppSettingsRepository,
    private val application: WhatToEatApplication
) : ViewModel() {

    private val _settingUiState = MutableStateFlow<SettingUIState>(SettingUIState.Loading)
    val homeUIState = _settingUiState.asStateFlow()

    init {
        getSettingUser()
    }

    fun handleScreenEvents(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.GetSetting -> getSettingUser()
            is SettingsScreenEvent.SaveSetting -> saveSettingUser(
                event.themeValue,
                event.ingredientUnitValue,
                event.username,
                event.pointLeft
            )
        }
    }

    private fun saveSettingUser(themeValue: Int?, ingredientUnitValue: Int?,username:String,pointLeft:Double) {
        var tempAppSetting: AppSetting = AppSetting(themeValue, ingredientUnitValue,username, pointLeft)

        viewModelScope.launch {
            dataStore.saveSetting(tempAppSetting)
            getSettingUser()
        }
    }

    private fun getSettingUser() {
        runBlocking {
            dataStore.getSetting().collect { dataStoreResult ->
                when (dataStoreResult) {
                    is DataStoreResult.Error -> {
                        _settingUiState.update {
                            SettingUIState.Error(
                                IllegalStateException(
                                    dataStoreResult.throwable
                                )
                            )
                        }
                    }

                    DataStoreResult.Loading -> SettingUIState.Loading
                    is DataStoreResult.Success -> _settingUiState.update {
                        application.appSetting.value = dataStoreResult.data
                        SettingUIState.Success(dataStoreResult.data)
                    }
                }
            }
        }
    }
}

sealed interface SettingsScreenEvent {
    data object GetSetting : SettingsScreenEvent
    data class SaveSetting(val themeValue: Int ?= null, val ingredientUnitValue: Int ?= null, val username: String="", val pointLeft: Double= Double.NaN) :
        SettingsScreenEvent
}