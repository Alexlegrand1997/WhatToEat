package com.example.whattoeat.ui.theme.screens.search


import com.example.whattoeat.models.IngredientSearch
import com.example.whattoeat.models.Results

sealed class SearchUiState {
    class Success(val recipes: Results): SearchUiState()
    data object Loading : SearchUiState()
    class Error (val exception: Exception): SearchUiState()
}

sealed class SearchIngredientUiState{
    class Success(val ingredients: List<IngredientSearch>): SearchIngredientUiState()
    data object Loading : SearchIngredientUiState()
    class Error (val exception: Exception): SearchIngredientUiState()
}