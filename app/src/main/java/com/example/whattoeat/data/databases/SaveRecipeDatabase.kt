package com.example.whattoeat.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.entities.RecipeSaveEntity


@Database(entities = [RecipeSaveEntity::class], version = 2, exportSchema = false)
abstract class SaveRecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}