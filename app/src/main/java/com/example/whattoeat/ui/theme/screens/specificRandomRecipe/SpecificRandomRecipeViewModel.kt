package com.example.whattoeat.ui.theme.screens.specificRandomRecipe

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whattoeat.core.DataStoreResult
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.ui.theme.screens.randomRecipe.RandomRecipeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.exitProcess

@HiltViewModel
class SpecificRandomRecipeViewModel @Inject constructor(private val _saveRecipeRepository: SaveRecipeRepository) :
    ViewModel() {

}
