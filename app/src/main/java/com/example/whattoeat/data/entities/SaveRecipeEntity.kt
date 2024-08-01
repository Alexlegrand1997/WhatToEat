package com.example.whattoeat.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "save_recipe_table", indices = [Index(value = ["idRecipe"], unique = true)])
data class SaveRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idRecipe: Int = 0,
    val title: String = "",
    val image: String = ""
)