package com.example.scos_mobile_app.data

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception
import java.util.*

class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )
    }

    val authToken: Flow<String?>
    get() = dataStore.data.map { preferences ->
        preferences[KEY_AUTH]
    }
    val username: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_USERNAME]
        }
    val expiration: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_EXPIRATION]
        }
    val sede: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_SEDE]
        }
    val role: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_ROLE]
        }
    val tipoDeServicioNombre: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_TIPO_DE_SERVICIO_NOMBRE]
        }
    val clienteId: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_CLIENTE_ID]
        }

    suspend fun saveAuthToken(authToken: String) {
        var claims:String = ""
        var authorities:String = ""
        var username:String = ""
        var expiration:String = ""
        var sede:String = ""
        var role:String = ""
        if (authToken != null) {
            claims = decodeJWT(authToken)
            authorities = JSONObject(claims).getString("authorities")
            username = JSONObject(claims).getString("sub")
            expiration = JSONObject(claims).getString("exp")
            role = JSONArray(authorities).getJSONObject(0).getString("authority")
            sede = JSONArray(authorities).getJSONObject(1).getString("authority")

            Log.e("------------>", claims)
            Log.e("------------>", authorities)
            Log.e("------------>", username)
            Log.e("------------>", expiration)
            Log.e("------------>", sede)
            Log.e("------------>", role)
        }
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
            preferences[KEY_USERNAME] = username
            preferences[KEY_EXPIRATION] = expiration
            preferences[KEY_SEDE] = sede
            preferences[KEY_ROLE] = role
        }
    }

    suspend fun saveTipoDeServicioNombre(tipoDeServicioNombre: String) {
        dataStore.edit { preferences ->
            preferences[KEY_TIPO_DE_SERVICIO_NOMBRE] = tipoDeServicioNombre
        }
    }

    suspend fun saveClienteId(clienteId: Long) {
        dataStore.edit { preferences ->
            preferences[KEY_CLIENTE_ID] = clienteId.toString()
        }
    }

    companion object {
        private val KEY_AUTH = preferencesKey<String>("key_auth")
        private val KEY_USERNAME = preferencesKey<String>("key_username")
        private val KEY_EXPIRATION = preferencesKey<String>("key_expiration")
        private val KEY_SEDE = preferencesKey<String>("key_sede")
        private val KEY_ROLE = preferencesKey<String>("key_role")
        private val KEY_TIPO_DE_SERVICIO_NOMBRE = preferencesKey<String>("key_tipo_de_servicio_nombre")
        private val KEY_CLIENTE_ID = preferencesKey<String>("key_cliente_id")
    }

    private fun decodeJWT(jwt: String) : String {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return "Requires SDK 26"
        val parts = jwt.split(".")
        return try {
            val charset = charset("UTF-8")
            val header = String(Base64.getUrlDecoder().decode(parts[0].toByteArray(charset)), charset)
            val payload = String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
            "$header"
            "$payload"
        } catch (e: Exception) {
            "Error parsing JWT:_ $e"
        }
    }
}