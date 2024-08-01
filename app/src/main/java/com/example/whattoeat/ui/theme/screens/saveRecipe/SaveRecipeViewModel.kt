package com.example.whattoeat.ui.theme.screens.saveRecipe

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveRecipeViewModel @Inject constructor(private val _saveRecipeRepository:SaveRecipeRepository): ViewModel() {


    var saveRecipe by mutableStateOf(SaveRecipeEntity())
        private set

    val getAllSaveRecipe = _saveRecipeRepository.getAllSaveRecipe()

    fun getAllRecipeSave()= viewModelScope.launch {
        _saveRecipeRepository.getAllSaveRecipe()
    }

}

