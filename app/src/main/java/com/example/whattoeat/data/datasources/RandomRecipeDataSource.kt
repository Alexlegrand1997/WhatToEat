package com.example.whattoeat.data.datasources


import com.example.whattoeat.BuildConfig.SPOONACULAR_API_KEY
import com.example.whattoeat.core.Constants
import com.example.whattoeat.models.Recipes
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RandomRecipeDataSource {

    private val json = Json { coerceInputValues=true; ignoreUnknownKeys = true }

    fun getOne(): Recipes {
        val(_,_,result) = Constants.RANDOM_RECIPE.httpGet(listOf("number" to 10)).appendHeader("x-api-key",
            SPOONACULAR_API_KEY
        ).responseJson()

        when (result){
            is Result.Success -> return json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error.exception
        }
    }


}