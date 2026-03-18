package com.universall.auth_impl.data.sources

import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.crypto.tink.Aead
import com.universall.auth_api.domain.entities.LocalAuthInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalDataSource @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val aead: Aead,
    private val json: Json
) {
    private val Context.authSecuredDatastore by preferencesDataStore(name = "auth_secured_store")

    private object AuthDataStoreInfo {
        val INFO_KEY = stringPreferencesKey("tokens")
    }

    private val authInfoFlow: Flow<LocalAuthInfo?> = context.authSecuredDatastore.data.map { preferences ->
        val b64Encoded = preferences[AuthDataStoreInfo.INFO_KEY] ?: return@map null

        try {
            val encrypted = Base64.decode(b64Encoded, Base64.NO_WRAP)
            val decrypted = aead.decrypt(encrypted, null)
            json.decodeFromString<LocalAuthInfo>(decrypted.decodeToString())
        } catch (e: Exception) {
            Log.e(this::class.simpleName, "Failed to load auth info: ${e.message}")
            return@map null
        }
    }

    suspend fun getAuthInfo(): LocalAuthInfo? {
        return authInfoFlow.firstOrNull()
    }

    suspend fun saveAuthInfo(info: LocalAuthInfo) {
        val json = json.encodeToString(info)
        val encrypted = aead.encrypt(json.toByteArray(), null)
        val b64Encoded = Base64.encodeToString(encrypted, Base64.NO_WRAP)

        context.authSecuredDatastore.edit {
            it[AuthDataStoreInfo.INFO_KEY] = b64Encoded
        }
    }

    suspend fun clearAuthInfo() {
        context.authSecuredDatastore.edit {
            it.remove(AuthDataStoreInfo.INFO_KEY)
        }
    }
}
