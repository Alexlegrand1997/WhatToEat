package com.example.whattoeat.di

import android.content.Context
import androidx.compose.runtime.Stable
import androidx.room.Room
import com.example.whattoeat.WhatToEatApplication
import com.example.whattoeat.data.repositories.AppSettingsRepository
import com.example.whattoeat.data.repositories.AppSettingsRepositoryImpl
import com.example.whattoeat.data.daos.RecipeDao
import com.example.whattoeat.data.databases.SaveRecipeDatabase
import com.example.whattoeat.data.repositories.SaveRecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Stable
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


    // For theme
    @Provides
    fun providesDatastoreRepo(
        @ApplicationContext context: Context
    ): AppSettingsRepository = AppSettingsRepositoryImpl(context)


    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): WhatToEatApplication {
        return app as WhatToEatApplication
    }




}