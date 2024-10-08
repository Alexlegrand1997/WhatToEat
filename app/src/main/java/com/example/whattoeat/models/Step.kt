package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class Step (
    val number: Int=0,
    val step: String ="",
    val ingredients: List<IngredientEquipment> = listOf(),
    val equipment : List<IngredientEquipment> = listOf(),
    val length : Length=Length()
)

@Serializable
data class Length(
    val number: Int =0,
    val unit: String =""
)