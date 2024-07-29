package com.example.whattoeat.ui.theme.screens.saveRecipe

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.data.daos.SaveRecipeUserDao.RecipeSave

import com.example.whattoeat.data.repositories.SaveRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveRecipeViewModel @Inject constructor(application: Application, private val _saveRecipeRepository:SaveRecipeRepository): AndroidViewModel(application) {


    fun getAllRecipeSave()= viewModelScope.launch {
        _saveRecipeRepository.getAllSaveRecipe()
    }

}

