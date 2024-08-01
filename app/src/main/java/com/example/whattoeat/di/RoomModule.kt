package com.example.whattoeat.di

import android.content.Context
import androidx.room.Room
import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.databases.SaveRecipeDatabase
import com.example.whattoeat.data.repositories.SaveRecipeRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideSaveRecipeDatabase(@ApplicationContext context: Context): SaveRecipeDatabase
    {
        return Room.databaseBuilder(context, SaveRecipeDatabase::class.java, "saveRecipe.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(saveRecipeDatabase: SaveRecipeDatabase): RecipeDao
    {
        return saveRecipeDatabase.recipeDao()
    }


    @Provides
    @Singleton
    fun provideSaveRecipeRepository(recipeDao: RecipeDao):SaveRecipeRepository= SaveRecipeRepository(recipeDao)

}