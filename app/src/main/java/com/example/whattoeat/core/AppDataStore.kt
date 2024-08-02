package com.example.whattoeat.core

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private val context: Context) {

    // TODO : Will be use for User Preferences
    // https://stackoverflow.com/questions/76472457/how-to-read-and-save-switch-data-with-datastore-in-jetpack-compose
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dataStore")
        val EXISTING_RECIPE = booleanPreferencesKey("data_Store")

    }
    val getData: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[EXISTING_RECIPE] ?: false
        }

    suspend fun saveData(existing: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[EXISTING_RECIPE] = existing
        }
    }
}

