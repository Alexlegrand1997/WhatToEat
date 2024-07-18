package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class Steps (
    val name:String="",
    val steps :List<Step> = listOf()
)