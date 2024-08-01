package com.example.whattoeat.data.repositories


import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.databases.SaveRecipeDatabase
import com.example.whattoeat.data.entities.RecipeSaveEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// Video use to make the repository with Room database
//https://www.youtube.com/watch?v=7cEqDV_c94k

class SaveRecipeRepository (private val recipeDao: RecipeDao) {

    fun getAllSaveRecipe(): Flow<List<RecipeSaveEntity>> {
        return recipeDao.getAll()
    }

    suspend fun insertOneRecipe(recipeSaveEntity: RecipeSaveEntity){
        recipeDao.insert(recipeSaveEntity)
    }
}