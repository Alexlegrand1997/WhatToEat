package com.example.whattoeat.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "save_recipe_table", indices = [Index(value = ["idRecipe"], unique = true)])
data class RecipeSaveEntity(
    @PrimaryKey(autoGenerate = true) var id:Int=1,
    @ColumnInfo(name="idRecipe") var idRecipe:Int?=0,
    @ColumnInfo(name= "title") var title:String?="",
    @ColumnInfo(name= "image") var image:String?=""
)