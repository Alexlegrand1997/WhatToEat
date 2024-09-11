package com.example.whattoeat.data.datasources

import com.example.whattoeat.BuildConfig.SPOONACULAR_API_KEY
import com.example.whattoeat.core.CalculatedConstant
import com.example.whattoeat.core.Constants
import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.data.repositories.AppSettingsRepository
import com.example.whattoeat.models.Recipe
import com.example.whattoeat.models.Recipes
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SpecificRecipeDataSource @Inject constructor(
    private val dataStore: AppSettingsRepository
) {

    private val _calculatedConstant: CalculatedConstant = CalculatedConstant()
    private val json = Json { coerceInputValues = true; ignoreUnknownKeys = true }

    suspend fun getOneSpecificRecipe(id: String): Recipe {
        val url = _calculatedConstant.UrlSpecificRecipe(id)
        val (_, response, result) = url.httpGet().appendHeader(
            "x-api-key",
            SPOONACULAR_API_KEY
        ).responseJson()

        var pointLeft =
            response.headers.entries.toTypedArray()[20].value.toTypedArray()[0].toDouble()
        dataStore.saveSetting(appSetting = AppSetting(pointLeft = pointLeft))

        when (result) {
            is Result.Success -> return json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error.exception
        }
    }
}