package com.example.whattoeat.data.datasources

import com.example.whattoeat.data.entities.RecipeSaveEntity
import kotlinx.coroutines.flow.Flow

interface SaveRecipeDataSource {

    fun getAllSaveRecipe() : Flow<List<RecipeSaveEntity>>
    suspend fun insertSaveRecipe(recipeSaveEntity: RecipeSaveEntity)
}