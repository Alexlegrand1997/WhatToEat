package com.example.whattoeat.data.datasources

import com.example.whattoeat.BuildConfig.SPOONACULAR_API_KEY
import com.example.whattoeat.core.CalculatedConstant
import com.example.whattoeat.core.Constants
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SpecificRecipeDataSource {

    private val _calculatedConstant:CalculatedConstant=CalculatedConstant()
    private val json = Json { coerceInputValues=true; ignoreUnknownKeys = true }

    fun getOneSpecificRecipe(id:String) : Recipe {
        val url = _calculatedConstant.UrlSpecificRecipe(id)
        val(_,_,result) = url.httpGet().appendHeader("x-api-key",
            SPOONACULAR_API_KEY
        ).responseJson()

        when (result){
            is Result.Success -> return json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error.exception
        }
    }
}