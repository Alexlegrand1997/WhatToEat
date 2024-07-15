package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe (
    val vegetarian: Boolean=false,
    val vegan: Boolean=false,
    val glutenFree: Boolean=false,
    val dairyFree: Boolean=false,
    val veryHealthy: Boolean=false,
    val cheap: Boolean=false,
    val veryPopular: Boolean=false,
    val sustainable: Boolean=false,
    val lowFodmap: Boolean=false,
    val weightWatcherSmartPoints :Int =0,
    val gaps:String ="",
//    val preparationMinutes:

)