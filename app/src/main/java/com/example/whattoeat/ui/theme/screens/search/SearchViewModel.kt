package com.example.whattoeat.ui.theme.screens.search

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.AlreadyLoadRandomRecipe
import com.example.whattoeat.core.AlreadyLoadSearchRecipe
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.repositories.IngredientRepository
import com.example.whattoeat.data.repositories.SearchRecipeRepository
import com.example.whattoeat.models.IngredientSearch
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.models.Results
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val _searchRecipeRepository: SearchRecipeRepository,
    private val _ingredientRepository: IngredientRepository
) : ViewModel() {
    private val _searchRecipeUiState = MutableStateFlow<SearchUiState>(SearchUiState.Loading)
    val searchUiState: StateFlow<SearchUiState> = _searchRecipeUiState.asStateFlow()

    private val _searchIngredientUiState =
        MutableStateFlow<SearchIngredientUiState>(SearchIngredientUiState.Loading)
    val searchIngredientUiState: StateFlow<SearchIngredientUiState> =
        _searchIngredientUiState.asStateFlow()

    var recipes: Results = Results()
//    var listIncludeIngredient: List<IngredientSearch> =  listOf()
    var listIncludeIngredient: MutableLiveData<List<IngredientSearch>> =  MutableLiveData<List<IngredientSearch>>(listOf())


    fun search(
        search: String,
        includeIngredient: String = "",
        excludeIngredient: String = "",
        offset: Int = 0,
        newSearch: Boolean = true
    ) {
        viewModelScope.launch {
            if (includeIngredient.isBlank() && excludeIngredient.isBlank()) withoutIngredient(
                search,
                offset,
                newSearch
            )
            else withIngredient(search, includeIngredient, excludeIngredient, offset, newSearch)

        }
    }

    private suspend fun withoutIngredient(search: String, offset: Int, newSearch: Boolean) {
        _searchRecipeRepository.retrieveRecipeWithoutIngredientList(search, offset)
            .collect { apiResult ->
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
                        setResult(apiResult.data, newSearch)
                        SearchUiState.Success(recipes)
                    }
                }

            }
    }


    private suspend fun withIngredient(
        search: String,
        includeIngredient: String,
        excludeIngredient: String,
        offset: Int,
        newSearch: Boolean
    ) {
        _searchRecipeRepository.retrieveRecipeWithIngredientList(
            search, includeIngredient, excludeIngredient, offset
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
                    setResult(apiResult.data, newSearch)
                    SearchUiState.Success(recipes)
                }
            }

        }
    }

    suspend fun searchIngredient(ingredient: String, isInclude: Boolean) {
        viewModelScope.launch {
            _ingredientRepository.getMultipleIngredientSuggestion(ingredient).collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Error -> _searchIngredientUiState.update {
                        SearchIngredientUiState.Error(
                            IllegalStateException(
                                apiResult.throwable
                            )
                        )
                    }

                    ApiResult.Loading -> SearchIngredientUiState.Loading
                    is ApiResult.Success -> _searchIngredientUiState.update {
                        SearchIngredientUiState.Success(apiResult.data)
                    }
                }
            }
        }
    }


    private fun setResult(results: Results, newSearch: Boolean) {
        if (newSearch) {
            recipes = results
        } else {
            recipes.results += results.results
            recipes.offset = results.offset
            recipes.totalResults = results.totalResults
            recipes.number += results.number
        }
    }
}