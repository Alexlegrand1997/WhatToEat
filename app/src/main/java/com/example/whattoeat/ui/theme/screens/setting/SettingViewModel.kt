package com.example.whattoeat.ui.theme.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.DataStoreResult
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
        getThemeValue()
    }

    fun handleScreenEvents(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.SetNewTheme -> setThemeValue(event.value)
            is SettingsScreenEvent.ThemeChanged -> getThemeValue()
        }
    }

    private fun setThemeValue(value: String) {
        viewModelScope.launch {
            dataStore.putThemeStrings(key = "theme", value = value)
            getThemeValue()
        }
    }

    private fun getThemeValue() = runBlocking {

        dataStore.getThemeStrings("theme").collect { dataStoreResult ->
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
                    application.theme.value = dataStoreResult.data.toString()
                    SettingUIState.Success(dataStoreResult.data)
                }
            }
        }


    }
}


sealed interface SettingsScreenEvent {
    data class SetNewTheme(val value: String) : SettingsScreenEvent
    data object ThemeChanged : SettingsScreenEvent
}