package com.example.whattoeat.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.whattoeat.core.DataStoreResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


//https://oguzhandogdu.medium.com/changing-the-application-theme-with-jetpack-datastore-c07c321fda79
interface AppSettingsRepository {
    suspend fun putThemeStrings(key: String, value: String)
    suspend fun getThemeStrings(key: String): Flow<DataStoreResult<String?>>
}

private val Context.themeDataStore: androidx.datastore.core.DataStore<Preferences> by preferencesDataStore(
    name = "THEME_KEYS"
)

class AppSettingsRepositoryImpl @Inject constructor(
    private val context: Context,
) : AppSettingsRepository {

    override suspend fun putThemeStrings(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.themeDataStore.edit {
            it[preferencesKey] = value
        }
    }

    override suspend fun getThemeStrings(key: String): Flow<DataStoreResult<String?>> {
        return flow {
            emit(DataStoreResult.Loading)
            val preferencesKey = stringPreferencesKey(key)
            val preference = context.themeDataStore.data.first()
            try {
                emit(DataStoreResult.Success(preference[preferencesKey]))

            }
            catch (ex:Exception){
                emit(DataStoreResult.Error(ex))
            }
        }.flowOn(Dispatchers.IO)
    }
}
