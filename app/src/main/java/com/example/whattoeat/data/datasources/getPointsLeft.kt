package com.example.whattoeat.data.datasources

import com.example.whattoeat.data.repositories.AppSetting
import com.example.whattoeat.data.repositories.AppSettingsRepository
import com.github.kittinunf.fuel.core.Response

suspend fun getPointsLeft(
    dataStore: AppSettingsRepository,
    response: Response
) {
    val pointLeft =
        response.headers.getValue("X-API-Quota-Left").toTypedArray()[0].toDouble()
    dataStore.saveSetting(appSetting = AppSetting(pointLeft = pointLeft))
}