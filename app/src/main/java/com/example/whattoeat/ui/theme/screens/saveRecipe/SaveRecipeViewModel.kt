package com.example.whattoeat.ui.theme.screens.saveRecipe

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.DataStoreResult
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveRecipeViewModel @Inject constructor(private val _saveRecipeRepository: SaveRecipeRepository) :
    ViewModel() {

    private val _saveRecipeUIState = MutableStateFlow<SaveRecipeUIState>(SaveRecipeUIState.Loading)
    val saveRecipeUIState: StateFlow<SaveRecipeUIState> = _saveRecipeUIState.asStateFlow()


    var saveRecipe by mutableStateOf(SaveRecipeEntity())
        private set


    val getAllSaveRecipe = _saveRecipeRepository.getAllSaveRecipe()

    fun getAllRecipeSave()= viewModelScope.launch {
        _saveRecipeRepository.getAllSaveRecipe()
    }
//
//    init {
//        getAllSaveRecipe()
//    }


//    private fun getAllSaveRecipe() = viewModelScope.launch {
//        _saveRecipeRepository.getAllSaveRecipe().collect { dataStoreResult ->
//
//            when (dataStoreResult) {
//                is DataStoreResult.Error -> _saveRecipeUIState.update {
//                    SaveRecipeUIState.Error(
//                        IllegalStateException(
//                            dataStoreResult.throwable
//                        )
//                    )
//                }
//
//                DataStoreResult.Loading -> SaveRecipeUIState.Loading
//                is DataStoreResult.Success -> _saveRecipeUIState.update {
//                    SaveRecipeUIState.Success(dataStoreResult.data)
//                }
//            }
//        }
//    }
}

