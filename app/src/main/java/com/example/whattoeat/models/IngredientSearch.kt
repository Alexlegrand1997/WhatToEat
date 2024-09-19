package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class IngredientSearch(
    val name: String = "",
    val image: String = ""
)