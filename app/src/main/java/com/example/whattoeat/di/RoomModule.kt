package com.example.whattoeat.di

import android.content.Context
import androidx.room.Room
import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.databases.SaveRecipeDatabase
import com.example.whattoeat.data.datasources.SaveRecipeDataSource
import com.example.whattoeat.data.rooms.RoomSaveRecipeDataSource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideSaveRecipeDatabase(@ApplicationContext context: Context): SaveRecipeDatabase
    {
        return Room.databaseBuilder(context, SaveRecipeDatabase::class.java, "saveRecipe.db")
            .build()
    }

    @Provides
    fun provideRecipeDao(saveRecipeDatabase: SaveRecipeDatabase): RecipeDao
    {
        return saveRecipeDatabase.recipeDao()
    }

    @Provides
    fun provideLocalSaveRecipeDataSource(recipeDao: RecipeDao): SaveRecipeDataSource{
        return RoomSaveRecipeDataSource(recipeDao)
    }
}