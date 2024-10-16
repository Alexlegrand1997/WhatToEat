package com.example.whattoeat.data.datasources

import com.example.whattoeat.BuildConfig.SPOONACULAR_API_KEY
import com.example.whattoeat.core.Constants
import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.data.repositories.AppSettingsRepository
import com.example.whattoeat.models.Recipes
import com.example.whattoeat.models.Results
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SearchRecipeDataSource @Inject constructor(
    private val dataStore: AppSettingsRepository

) {
    private val json = Json { coerceInputValues = true; ignoreUnknownKeys = true }

    suspend fun getSearchWithoutIngredientList(search:String,offset:Int): Results {
        val (_, response, result) = Constants.SEARCH_RECIPE.httpGet(
            listOf(
                "query" to search,
                "number" to 10,
                "fillIngredients" to true,
                "addRecipeInstructions" to true,
                "addRecipeInformation" to true,
                "offset" to offset
            )
        )
            .appendHeader(
                "x-api-key",
                SPOONACULAR_API_KEY
            ).responseJson()

        getPointsLeft(dataStore, response)


        when (result) {
            is Result.Success -> return json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error.exception
        }
    }


    suspend fun getSearchWithIngredientList(
        search:String,
        includeIngredient: String,
        excludeIngredient: String,
        offset: Int
    ): Results {
        val (_, response, result) = Constants.SEARCH_RECIPE.httpGet(
            listOf(
                "query" to search,
                "number" to 10,
                "fillIngredients" to true,
                "addRecipeInstructions" to true,
                "addRecipeInformation" to true,
                "includeIngredients" to includeIngredient,
                "excludeIngredients" to excludeIngredient,
                "offset" to offset
            )
        )
            .appendHeader(
                "x-api-key",
                SPOONACULAR_API_KEY
            ).responseJson()

        getPointsLeft(dataStore, response)

        when (result) {
            is Result.Success -> return json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error.exception
        }
    }

}