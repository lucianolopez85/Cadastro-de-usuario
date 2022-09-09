package com.example.cadastro_de_usuario.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.map

class UserManager(val context: Context) {


    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_PREFERENCES_NAME")
    }

    suspend fun saveListUsers(user: User) {
        context.dataStore.edit { preferences ->
            var allUsers = preferences.
            var key = allUsers.size
            preferences[stringPreferencesKey()]

        }
    }


    suspend fun saveListUser(user: User) {
        var key = ""
        context.dataStore.data.map { preferences ->
            key = preferences[stringPreferencesKey("EMAIL")].orEmpty()
        }

        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = Gson().toJson(user)
        }
    }

    suspend fun saveValues(nome: String, email: String, senha: String, tipo: Int) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("NOME")] = nome
            preferences[stringPreferencesKey("EMAIL")] = email
            preferences[stringPreferencesKey("SENHA")] = senha
            preferences[intPreferencesKey("TIPO")] = tipo
        }
    }



    suspend fun readDataUser() = context.dataStore.data.map { preferences ->
        User(
            name = preferences[stringPreferencesKey("NOME")].orEmpty(),
            email = preferences[stringPreferencesKey("EMAIL")].orEmpty(),
            senha = preferences[stringPreferencesKey("SENHA")].orEmpty(),
            tipo = preferences[intPreferencesKey("TIPO")]?:0,
        )
    }
}