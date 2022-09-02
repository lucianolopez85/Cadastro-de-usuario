package com.example.cadastro_de_usuario.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class UserManager(val context: Context) {

    companion object {

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "USER_PREFERENCES_NAME")
    }

    suspend fun saveValues( nome: String, email: String, senha: Int, tipo: Int) {
        context?.dataStore?.edit { preferences ->
            preferences[stringPreferencesKey("NOME")] = nome
            preferences[stringPreferencesKey("EMAIL")] = email
            preferences[intPreferencesKey("SENHA")] = senha
            preferences[intPreferencesKey("TIPO")] = tipo
        }
    }

    suspend fun readDataUser() = context.dataStore.data.map { preferences ->
        User(
            name = preferences[stringPreferencesKey("NOME")].orEmpty(),
            email = preferences[stringPreferencesKey("EMAIL")].orEmpty(),
            senha = preferences[intPreferencesKey("SENHA")]?: 0,
            tipo = preferences[intPreferencesKey("TIPO")]?: 0
        )
    }
}