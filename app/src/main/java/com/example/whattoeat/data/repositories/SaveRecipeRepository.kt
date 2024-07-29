package com.example.whattoeat.data.repositories


import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.databases.SaveRecipeDatabase
import com.example.whattoeat.data.datasources.SaveRecipeDataSource
import com.example.whattoeat.data.entities.RecipeSaveEntity
import javax.inject.Inject


// Video use to make the repository with Room database
//https://www.youtube.com/watch?v=7cEqDV_c94k

class SaveRecipeRepository @Inject constructor(private val localDataSource: SaveRecipeDataSource) {

    suspend fun getAllSaveRecipe() {
        localDataSource.getAllSaveRecipe()
    }

    suspend fun insertOneRecipe(recipeSaveEntity: RecipeSaveEntity){
        localDataSource.insertSaveRecipe(recipeSaveEntity)
    }
}