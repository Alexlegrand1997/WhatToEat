package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class Results(
    var results:List<Recipe> = listOf(),
    var offset:Int=0,
    var number:Int=0,
    var totalResults: Int=0
)