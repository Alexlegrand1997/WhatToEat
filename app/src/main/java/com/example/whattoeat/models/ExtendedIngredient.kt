package com.example.whattoeat.models
import kotlinx.serialization.Serializable

@Serializable
data class ExtendedIngredient (
    val id: Int,
    val aisle:String ="",
    val image: String ="",
    val consistency: String ="",
    val name :String ="",
    val nameClean: String ="",
    val original: String ="",
    val originalName: String ="",
    val amount: Float =0.0F,
    val unit: String ="",
    val meta: List<String> = listOf(),
    val measures: Measures = Measures()

)

@Serializable
data class Measures (
    val us:Measure = Measure(),
    val metric: Measure =Measure()
)
