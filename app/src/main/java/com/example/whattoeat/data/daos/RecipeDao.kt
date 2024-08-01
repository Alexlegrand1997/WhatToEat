package com.example.whattoeat.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whattoeat.data.daos.SaveRecipeUserDao.RecipeSave
import com.example.whattoeat.data.entities.RecipeSaveEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM save_recipe_table")
    fun getAll(): Flow<List<RecipeSaveEntity>>

    @Query("SELECT * FROM save_recipe_table WHERE id = :idRecipe")
    suspend fun getOne(idRecipe: Int): RecipeSaveEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg recipeSaveEntity: RecipeSaveEntity)

    @Delete
    suspend fun delete(recipeSaveEntity: RecipeSaveEntity)

}