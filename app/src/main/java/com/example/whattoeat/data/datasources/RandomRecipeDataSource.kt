package com.example.whattoeat.data.datasources


import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whattoeat.BuildConfig.SPOONACULAR_API_KEY
import com.example.whattoeat.core.Constants
import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.data.repositories.AppSettingsRepository
import com.example.whattoeat.data.repositories.AppSettingsRepositoryImpl
import com.example.whattoeat.models.Recipes
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class RandomRecipeDataSource @Inject constructor(
    private val dataStore: AppSettingsRepository

) {
    private val json = Json { coerceInputValues = true; ignoreUnknownKeys = true }

    suspend fun getOne(): Recipes {
        val (_, response, result) = Constants.RANDOM_RECIPE.httpGet(listOf("number" to 10))
            .appendHeader(
                "x-api-key",
                SPOONACULAR_API_KEY
            ).responseJson()

        var pointLeft = response.headers.entries.toTypedArray()[20].value.toTypedArray()[0].toDouble()
         dataStore.saveSetting(appSetting = AppSetting(pointLeft = pointLeft))


        when (result) {
            is Result.Success -> return json.decodeFromString(result.value.content)
            is Result.Failure -> throw result.error.exception
        }
    }


}