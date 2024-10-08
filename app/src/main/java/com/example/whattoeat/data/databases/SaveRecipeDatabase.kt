package com.example.whattoeat.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.entities.SaveRecipeEntity


@Database(entities = [SaveRecipeEntity::class], version = 3, exportSchema = false)
abstract class SaveRecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}