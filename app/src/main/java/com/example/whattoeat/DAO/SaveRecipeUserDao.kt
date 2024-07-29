package com.example.whattoeat.DAO

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase


class SaveRecipeUserDao{

    @Entity
    data class Recipe(
        @PrimaryKey val id:Int,
        @ColumnInfo(name= "title") val title:String?,
        @ColumnInfo(name= "image") val image:String?
    )

    @Database(entities = [Recipe::class], version = 1)
    abstract class AppDatabase : RoomDatabase(){
        abstract fun recipeDao() : RecipeDao
    }


    @Dao
    interface RecipeDao {

        @Query("SELECT * FROM recipe")
        fun getAll(): List<Recipe>

        @Insert
        fun insert(vararg recipe: Recipe)

        @Delete
        fun delete(recipe: Recipe)

    }
    
}