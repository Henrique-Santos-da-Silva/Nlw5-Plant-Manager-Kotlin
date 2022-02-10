package br.com.rocketseat.nextlevelweek.plantmanager.repositories

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.rocketseat.nextlevelweek.plantmanager.utils.dataStoreInstance
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserDbRepository @Inject constructor(private val context: Context) {

    suspend fun insertUser(key: String, value: String) {
        val preferencesKey: Preferences.Key<String> = stringPreferencesKey(key)
        context.dataStoreInstance.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    suspend fun getUser(key: String): String? {
        val preferencesKey: Preferences.Key<String> = stringPreferencesKey(key)
        val preferences: Preferences = context.dataStoreInstance.data.first()
        return preferences[preferencesKey]
    }
}