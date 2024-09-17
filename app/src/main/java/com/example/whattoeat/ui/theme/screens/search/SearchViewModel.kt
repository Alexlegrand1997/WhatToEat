package com.example.whattoeat.ui.theme.screens.search

import androidx.lifecycle.ViewModel
import com.example.whattoeat.data.repositories.SearchRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel   @Inject constructor(
    private val _searchRecipeRepository: SearchRecipeRepository
) : ViewModel(){
    private val _searchRecipeUiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val searchUiState : StateFlow<SearchUiState> = _searchRecipeUiState.asStateFlow()

    fun search(title: String, includeIngredient:String="", excludeIngredient:String =""){

    }
}