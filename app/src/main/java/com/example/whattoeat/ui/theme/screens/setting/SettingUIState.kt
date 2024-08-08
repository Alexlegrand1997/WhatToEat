package com.example.whattoeat.ui.theme.screens.setting

sealed class SettingUIState {
    class Success(val themeValue: String?=null): SettingUIState()
    data object Loading : SettingUIState()
    class Error (val exception: Exception): SettingUIState()
}


