package com.example.whattoeat.models
import kotlinx.serialization.Serializable

@Serializable
data class Measure (
    val amount: Float = 0.0F,
    val unitShort: String = "",
    val unitLong: String =""
)