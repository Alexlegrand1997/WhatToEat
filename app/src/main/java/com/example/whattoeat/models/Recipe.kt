package com.example.whattoeat.models

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val vegetarian: Boolean=false,
    val vegan: Boolean=false,
    val glutenFree: Boolean=false,
    val dairyFree: Boolean=false,
    val veryHealthy: Boolean=false,
    val cheap: Boolean=false,
    val veryPopular: Boolean=false,
    val sustainable: Boolean=false,
    val lowFodmap: Boolean=false,
    val weightWatcherSmartPoints:Int =0,
    val gaps:String ="",
    val preparationMinutes:String?="" , // TODO : TO VALIDATE
    val cookingMinutes: Double? =0.0, //TODO : TO VALIDATE
    val aggregateLikes:Int =0,
    val healthScore: Int =0,
    val creditsText:String ="",
    val sourceName:String="",
    val pricePerServing:Float =0.0f,
    val extendedIngredients:List<ExtendedIngredient> = listOf(),
    val id: Int,
    val title:String ="",
    val readyInMinutes:Int=0,
    val servings:Int=0,
    val sourceUrl:String="",
    val image:String="",
    val imageType:String="",
    val summary:String="",
    val cuisines: List<String> = listOf(),
    val dishTypes: List<String> = listOf(),
    val diets: List<String> = listOf(),
    val occasions: List<String> = listOf(),
    val instructions:String = "",
    val analyzedInstructions:List<Step> = listOf(),
    val originalId: Int? =0,
    val spoonacularScore: Float=0.0f,
    val spoonacularSourceUrl:String=""

)