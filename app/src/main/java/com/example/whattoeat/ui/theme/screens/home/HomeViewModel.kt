package com.example.whattoeat.ui.theme.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.core.userDataStore.AppSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStore: AppSettingsRepository,
    private val application: WhatToEatApplication
) : ViewModel() {
    private val _settingsState = MutableStateFlow(SettingsScreenState())
    val settingsState = _settingsState.asStateFlow()

    fun handleScreenEvents(event: SettingsScreenEvent) {
        when (event) {
            is SettingsScreenEvent.OpenThemeDialog -> {
                openTheme(event.open)
            }

            is SettingsScreenEvent.SetNewTheme -> setThemeValue(event.value)
            is SettingsScreenEvent.ThemeChanged -> getThemeValue()
        }
    }

    private fun openTheme(open: Boolean) {
        viewModelScope.launch {
            _settingsState.update { it.copy(openThemeDialog = open) }
        }
    }

    private fun setThemeValue(value: String) {
        viewModelScope.launch {
            dataStore.putThemeStrings(key = "theme", value = value)
            getThemeValue()
        }
    }

    private fun getThemeValue() = runBlocking{
        val theme = dataStore.getThemeStrings(key = "theme").first()
        theme?.let { value ->
            _settingsState.updateAndGet {
                it.copy(getThemeValue = value)
            }
            application.theme.value = value
        }
    }

}



data class SettingsScreenState(
    val openThemeDialog: Boolean = false,
    val getThemeValue: String? = null,
)

sealed interface SettingsScreenEvent {
    data class OpenThemeDialog(val open: Boolean = false) : SettingsScreenEvent
    data class SetNewTheme(val value: String) : SettingsScreenEvent
    data object ThemeChanged : SettingsScreenEvent
}