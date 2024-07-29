package com.example.whattoeat.data.rooms

import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.datasources.SaveRecipeDataSource
import com.example.whattoeat.data.entities.RecipeSaveEntity
import kotlinx.coroutines.flow.Flow

class RoomSaveRecipeDataSource(private val recipeDao: RecipeDao) : SaveRecipeDataSource {

    override fun getAllSaveRecipe(): Flow<List<RecipeSaveEntity>> {
        return recipeDao.getAll()
    }

    override suspend fun insertSaveRecipe(recipeSaveEntity: RecipeSaveEntity) {
        return recipeDao.insert(recipeSaveEntity)
    }
}