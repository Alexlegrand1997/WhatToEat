package com.example.whattoeat.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.whattoeat.core.Constants.INGREDIENT_UNIT_KEY
import com.example.whattoeat.core.Constants.SETTING_DATASTORE
import com.example.whattoeat.core.Constants.THEME_KEY
import com.example.whattoeat.core.Constants.USERNAME_KEY
import com.example.whattoeat.core.DataStoreResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// TODO : Will be use for User Preferences
// https://stackoverflow.com/questions/76472457/how-to-read-and-save-switch-data-with-datastore-in-jetpack-compose
// https://oguzhandogdu.medium.com/changing-the-application-theme-with-jetpack-datastore-c07c321fda79

interface AppSettingsRepository {
    suspend fun getSetting(): Flow<DataStoreResult<AppSetting>>
    suspend fun saveSetting(appSetting: AppSetting)
}

private val Context.settingDataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(
    SETTING_DATASTORE
)

class AppSettingsRepositoryImpl @Inject constructor(
    private val context: Context,
) : AppSettingsRepository {


    companion object {
        val THEME = stringPreferencesKey(THEME_KEY)
        val INGREDIENT_UNIT = stringPreferencesKey(INGREDIENT_UNIT_KEY)
        val USERNAME = stringPreferencesKey(USERNAME_KEY)
    }

    override suspend fun getSetting(): Flow<DataStoreResult<AppSetting>> {
        return flow {
            emit(DataStoreResult.Loading)
            val themeKey = stringPreferencesKey(THEME_KEY)
            val ingredientUnitKey = stringPreferencesKey(INGREDIENT_UNIT_KEY)
            val usernameKey = stringPreferencesKey(USERNAME_KEY)
            val preference = context.settingDataStore.data.map {
                AppSetting(
                    theme = it[themeKey] ?: "",
                    ingredientUnit = it[ingredientUnitKey] ?: "",
                    username = it[usernameKey] ?: ""
                )
            }.first()
            try {
                emit(DataStoreResult.Success(preference))

            } catch (ex: Exception) {
                emit(DataStoreResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveSetting(appSetting: AppSetting) {
        context.settingDataStore.edit {
            if (appSetting.theme.isNotBlank()) {
                it[THEME] = appSetting.theme
            }
            if (appSetting.ingredientUnit.isNotBlank()) {
                it[INGREDIENT_UNIT] = appSetting.ingredientUnit
            }
            if(appSetting.username.isNotBlank()){
                it[USERNAME] = appSetting.username
            }
        }
    }

}

data class AppSetting(
    val theme: String,
    val ingredientUnit: String,
    val username: String
)



