package com.example.whattoeat.ui.theme.screens.randomRecipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.repositories.RandomRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RandomRecipeViewModel(application: Application): AndroidViewModel(application) {
    private val _randomRecipeUIState = MutableStateFlow<RandomRecipeUIState>(RandomRecipeUIState.Loading)
    val randomRecipeUIState: StateFlow<RandomRecipeUIState> = _randomRecipeUIState.asStateFlow()

    private val _randomRecipeRepository = RandomRecipeRepository()

    init {
        getRandomRecipe()
    }


   private fun getRandomRecipe(){
        viewModelScope.launch{
            _randomRecipeRepository.retrieveOne().collect{ apiResult ->
                when(apiResult){
                    is ApiResult.Error ->{
                        _randomRecipeUIState.update{
                            RandomRecipeUIState.Error(
                                IllegalStateException(
                                    apiResult.throwable
                                )
                            )
                        }
                    }
                    ApiResult.Loading ->RandomRecipeUIState.Loading
                    is ApiResult.Success->{
                        _randomRecipeUIState.update {
                            RandomRecipeUIState.Success(apiResult.data)
                        }
                    }
                }
            }
        }
    }



}