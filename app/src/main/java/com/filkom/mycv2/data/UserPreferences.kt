package com.filkom.mycv2.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val NIM_KEY = stringPreferencesKey("nim")
        val NAMA_KEY = stringPreferencesKey("nama")
        val EMAIL_KEY = stringPreferencesKey("email")
        val ALAMAT_KEY = stringPreferencesKey("alamat")
        val PASSWORD_KEY = stringPreferencesKey("password")
    }

    suspend fun saveUser(nim: String, nama: String, email: String, alamat: String, password: String) {
        context.dataStore.edit { prefs ->
            prefs[NIM_KEY] = nim
            prefs[NAMA_KEY] = nama
            prefs[EMAIL_KEY] = email
            prefs[ALAMAT_KEY] = alamat
            prefs[PASSWORD_KEY] = password
        }
    }

    fun getUser() = context.dataStore.data.map { prefs ->
        UserData(
            nim = prefs[NIM_KEY] ?: "",
            nama = prefs[NAMA_KEY] ?: "",
            email = prefs[EMAIL_KEY] ?: "",
            alamat = prefs[ALAMAT_KEY] ?: "",
            password = prefs[PASSWORD_KEY] ?: ""
        )
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}

data class UserData(
    val nim: String = "",
    val nama: String = "",
    val email: String = "",
    val alamat: String = "",
    val password: String = ""
)
