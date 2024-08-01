package com.example.whattoeat.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whattoeat.data.entities.SaveRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM save_recipe_table")
    fun getAll(): Flow<List<SaveRecipeEntity>>

    @Query("SELECT * FROM save_recipe_table WHERE id = :idRecipe")
    suspend fun getOne(idRecipe: Int): SaveRecipeEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg saveRecipeEntity: SaveRecipeEntity)

    @Delete
    suspend fun delete(saveRecipeEntity: SaveRecipeEntity)

}