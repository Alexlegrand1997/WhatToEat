package com.example.whattoeat.ui.theme.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.AlreadyLoadRandomRecipe
import com.example.whattoeat.core.AlreadyLoadSearchRecipe
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.repositories.SearchRecipeRepository
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.models.Results
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val _searchRecipeRepository: SearchRecipeRepository
) : ViewModel() {
    private val _searchRecipeUiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val searchUiState: StateFlow<SearchUiState> = _searchRecipeUiState.asStateFlow()

    var recipes: Results = Results()

    fun search(search: String, includeIngredient: String = "", excludeIngredient: String = "") {
        viewModelScope.launch {
            if (includeIngredient.isBlank() && excludeIngredient.isBlank()) withoutIngredient(search)
            else withIngredient(search, includeIngredient, excludeIngredient)

        }
    }

    private suspend fun withoutIngredient(search: String) {
        _searchRecipeRepository.retrieveRecipeWithoutIngredientList(search).collect { apiResult ->
            when (apiResult) {
                is ApiResult.Error -> _searchRecipeUiState.update {
                    SearchUiState.Error(
                        IllegalStateException(
                            apiResult.throwable
                        )
                    )
                }

                ApiResult.Loading -> SearchUiState.Loading
                is ApiResult.Success -> _searchRecipeUiState.update {
                    AlreadyLoadSearchRecipe.setLoadedRecipeState(apiResult.data)
                    recipes = apiResult.data
                    SearchUiState.Success(apiResult.data)
                }
            }

        }
    }



    private suspend fun withIngredient(
        search: String, includeIngredient: String, excludeIngredient: String
    ) {
        _searchRecipeRepository.retrieveRecipeWithIngredientList(
            search, includeIngredient, excludeIngredient
        ).collect { apiResult ->
            when (apiResult) {
                is ApiResult.Error -> _searchRecipeUiState.update {
                    SearchUiState.Error(
                        IllegalStateException(
                            apiResult.throwable
                        )
                    )
                }

                ApiResult.Loading -> SearchUiState.Loading
                is ApiResult.Success -> _searchRecipeUiState.update {
                    AlreadyLoadSearchRecipe.setLoadedRecipeState(apiResult.data)
                    SearchUiState.Success(apiResult.data)
                }
            }

        }
    }
}