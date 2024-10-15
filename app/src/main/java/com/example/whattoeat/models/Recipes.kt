package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipes(
    val recipes:List<Recipe> = listOf()
)
