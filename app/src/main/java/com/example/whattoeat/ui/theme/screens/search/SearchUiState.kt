package com.example.whattoeat.ui.theme.screens.search

import com.example.whattoeat.models.Recipes

sealed class SearchUiState {
    class Success(val recipes: Recipes): SearchUiState()
    data object Loading : SearchUiState()
    class Error (val exception: Exception): SearchUiState()
}