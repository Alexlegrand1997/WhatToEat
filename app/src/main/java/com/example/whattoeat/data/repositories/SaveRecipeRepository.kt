package com.example.whattoeat.data.repositories


import com.example.whattoeat.core.ApiResult
import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.datasources.RandomRecipeDataSource
import com.example.whattoeat.data.datasources.SpecificRecipeDataSource
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


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