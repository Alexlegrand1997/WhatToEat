package com.example.whattoeat.ui.theme.screens.search


import com.example.whattoeat.models.Results

sealed class SearchUiState {
    class Success(val recipes: Results): SearchUiState()
    data object Loading : SearchUiState()
    class Error (val exception: Exception): SearchUiState()
}