package com.example.whattoeat.ui.theme.screens.randomRecipe

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.entities.RecipeSaveEntity
import com.example.whattoeat.data.repositories.RandomRecipeRepository
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import com.example.whattoeat.models.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomRecipeViewModel (application: Application): AndroidViewModel(application) {
    private val _randomRecipeUIState = MutableStateFlow<RandomRecipeUIState>(RandomRecipeUIState.Loading)
    val randomRecipeUIState: StateFlow<RandomRecipeUIState> = _randomRecipeUIState.asStateFlow()

    private val _randomRecipeRepository = RandomRecipeRepository()

    init {
        getRandomRecipe()
    }

    fun getRandomRecipe(){
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


    fun saveRecipe(recipe: Recipe) = viewModelScope.launch{
        val saveRecipe =RecipeSaveEntity(idRecipe = recipe.id, title = recipe.title, image = recipe.image)
//        _saveRecipeRepository.insertOneRecipe(saveRecipe)
    }

}