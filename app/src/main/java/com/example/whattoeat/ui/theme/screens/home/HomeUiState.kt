package com.example.whattoeat.ui.theme.screens.home

import com.example.whattoeat.data.repositories.AppSetting

sealed class HomeUiState {
    class Success(val appSetting: AppSetting): HomeUiState()
    data object Loading : HomeUiState()
    class Error (val exception: Exception): HomeUiState()
}