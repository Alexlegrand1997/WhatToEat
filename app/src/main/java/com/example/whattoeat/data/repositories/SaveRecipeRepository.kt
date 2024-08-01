package com.example.whattoeat.data.repositories


import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.entities.SaveRecipeEntity
import kotlinx.coroutines.flow.Flow


// Video use to make the repository with Room database
//https://www.youtube.com/watch?v=7cEqDV_c94k

class SaveRecipeRepository (private val recipeDao: RecipeDao) {

    fun getAllSaveRecipe(): Flow<List<SaveRecipeEntity>> {
        return recipeDao.getAll()
    }

    suspend fun insertOneRecipe(saveRecipeEntity: SaveRecipeEntity){
        recipeDao.insert(saveRecipeEntity)
    }
}