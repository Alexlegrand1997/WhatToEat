package com.example.whattoeat.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.whattoeat.core.Constants
import com.example.whattoeat.core.DataStoreResult
import com.example.whattoeat.data.entities.SaveRecipeEntity
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM ${Constants.SAVE_RECIPE_DB}")
    fun getAll(): Flow<List<SaveRecipeEntity>>

    @Query("SELECT * FROM ${Constants.SAVE_RECIPE_DB} WHERE idRecipe = :idRecipe")
    fun getOne(idRecipe: Int): Flow<SaveRecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg saveRecipeEntity: SaveRecipeEntity)

    @Query("DELETE FROM ${Constants.SAVE_RECIPE_DB} WHERE idRecipe =:idRecipe")
    suspend fun delete(idRecipe: Int)

    @Query("SELECT COUNT(*) FROM ${Constants.SAVE_RECIPE_DB} WHERE idRecipe =:idRecipe")
    fun isSaveRecipe(idRecipe: Int) : Flow<Boolean>

}