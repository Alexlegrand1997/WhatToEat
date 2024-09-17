package com.example.whattoeat.ui.theme.screens.randomRecipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.AlreadyLoadRandomRecipe
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.repositories.RandomRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomRecipeViewModel @Inject constructor(
    private val _randomRecipeRepository: RandomRecipeRepository
    ) :
    ViewModel() {
    private val _randomRecipeUIState =
        MutableStateFlow<RandomRecipeUIState>(RandomRecipeUIState.Loading)
    val randomRecipeUIState: StateFlow<RandomRecipeUIState> = _randomRecipeUIState.asStateFlow()


    init {
        if (AlreadyLoadRandomRecipe.getLoadedRecipeState() !=null) {
            getRandomRecipe()
        }
        else{
            RandomRecipeUIState.Success(AlreadyLoadRandomRecipe.getLoadedRecipeState())
        }
    }

    fun getRandomRecipe() {

        viewModelScope.launch {
            _randomRecipeRepository.retrieveOne().collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Error -> {
                        _randomRecipeUIState.update {
                            RandomRecipeUIState.Error(
                                IllegalStateException(
                                    apiResult.throwable
                                )
                            )
                        }
                    }

                    ApiResult.Loading -> RandomRecipeUIState.Loading
                    is ApiResult.Success -> {
                        _randomRecipeUIState.update {
                            AlreadyLoadRandomRecipe.setLoadedRecipeState(apiResult.data)
                            RandomRecipeUIState.Success(apiResult.data)
                        }
                    }
                }
            }
        }
    }

//
//    fun saveRecipe(recipe: Recipe) = viewModelScope.launch {
//        val saveRecipe =
//            SaveRecipeEntity(idRecipe = recipe.id, title = recipe.title, image = recipe.image)
//        _saveRecipeRepository.insertOneRecipe(saveRecipe)
//    }

}