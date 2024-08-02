package com.example.whattoeat.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.whattoeat.core.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = Constants.SAVE_RECIPE_DB, indices = [Index(value = ["idRecipe"], unique = true)])
data class SaveRecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idRecipe: Int = 0,
    val title: String = "",
    val image: String = ""
)