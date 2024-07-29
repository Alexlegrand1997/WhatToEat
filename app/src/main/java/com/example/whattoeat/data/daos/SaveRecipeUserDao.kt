package com.example.whattoeat.data.daos

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.android.parcel.Parcelize


class SaveRecipeUserDao{

    @Parcelize
    @Entity
    data class RecipeSave(
        @PrimaryKey var id:Int?=0,
        @ColumnInfo(name= "title") var title:String?="",
        @ColumnInfo(name= "image") var image:String?=""
    )

    @Database(entities = [RecipeSave::class], version = 1, exportSchema = false)
    abstract class AppDatabase : RoomDatabase(){
        abstract fun recipeDao() : RecipeDao
    }


    @Dao
    interface RecipeDao {

        @Query("SELECT * FROM recipesave")
        suspend fun getAll(): List<RecipeSave>

        @Query("SELECT * FROM recipesave WHERE id = :idRecipe")
        suspend fun getOne(idRecipe: Int): RecipeSave

        @Insert
        suspend fun insert(vararg recipeSave: RecipeSave)

        @Delete
        suspend fun delete(recipe: RecipeSave)

    }
    
}