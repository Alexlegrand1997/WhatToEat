package com.example.whattoeat.ui.theme.screens.setting

import com.example.whattoeat.data.repositories.AppSetting

sealed class SettingUIState {
    class Success(val appSetting: AppSetting): SettingUIState()
    data object Loading : SettingUIState()
    class Error (val exception: Exception): SettingUIState()
}



