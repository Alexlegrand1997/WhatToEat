package com.example.whattoeat.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.whattoeat.core.Constants.DEFAULT_INGREDIENT_UNIT_VALUE
import com.example.whattoeat.core.Constants.DEFAULT_THEME_VALUE
import com.example.whattoeat.core.Constants.INGREDIENT_UNIT_KEY
import com.example.whattoeat.core.Constants.POINT_LEFT_KEY
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
        val THEME =  intPreferencesKey(THEME_KEY)
        val INGREDIENT_UNIT = intPreferencesKey(INGREDIENT_UNIT_KEY)
        val USERNAME = stringPreferencesKey(USERNAME_KEY)
        val POINT_LEFT = doublePreferencesKey(POINT_LEFT_KEY)
    }

    override suspend fun getSetting(): Flow<DataStoreResult<AppSetting>> {
        return flow {
            emit(DataStoreResult.Loading)
            val themeKey = intPreferencesKey(THEME_KEY)
            val ingredientUnitKey = intPreferencesKey(INGREDIENT_UNIT_KEY)
            val usernameKey = stringPreferencesKey(USERNAME_KEY)
            val pointLeftKey = doublePreferencesKey(POINT_LEFT_KEY)
            val preference = context.settingDataStore.data.map {
                AppSetting(
                    theme = it[themeKey] ?: DEFAULT_THEME_VALUE,
                    ingredientUnit = it[ingredientUnitKey] ?: DEFAULT_INGREDIENT_UNIT_VALUE,
                    username = it[usernameKey] ?: "",
                    pointLeft=it[pointLeftKey]?: Double.NaN
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
            if (appSetting.theme !=null ) {
                it[THEME] = appSetting.theme
            }
            if (appSetting.ingredientUnit != null) {
                it[INGREDIENT_UNIT] = appSetting.ingredientUnit
            }
            if(appSetting.username.isNotBlank()){
                it[USERNAME] = appSetting.username
            }
            if(!appSetting.pointLeft.isNaN()){
                it[POINT_LEFT] = appSetting.pointLeft
            }
        }
    }

}

data class AppSetting(
    val theme: Int ?=null,
    val ingredientUnit: Int?=null,
    val username: String="",
    val pointLeft: Double= Double.NaN
)



