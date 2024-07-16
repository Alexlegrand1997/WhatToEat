package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class IngredientEquipment (
    val id: Int,
    val name: String="",
    val localizedName: String="",
    val image: String=""
)