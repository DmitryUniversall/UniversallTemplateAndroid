package com.universall.auth_impl.data.sources

import android.os.Build
import com.universall.appcore.network.api.ApiClient
import com.universall.auth_api.domain.schemas.LoginSchema
import com.universall.auth_api.domain.schemas.RegisterSchema
import com.universall.auth_impl.data.dto.get_actual_auth_context.GetCurrentAuthContextResponseDTO
import com.universall.auth_impl.data.dto.login.LoginRequestDTO
import com.universall.auth_impl.data.dto.login.LoginResponseDTO
import com.universall.auth_impl.data.dto.refresh.RefreshRequestDTO
import com.universall.auth_impl.data.dto.refresh.RefreshResponseDTO
import com.universall.auth_impl.data.dto.register.RegisterRequestDTO
import com.universall.auth_impl.data.dto.register.RegisterResponseDTO
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthNetworkDataSource @Inject constructor(
    private val apiClient: ApiClient
) {
    private val authFeaturePathSegment = "/auth"

    private val registerPathSegment = "register"
    private val loginPathSegment = "login"
    private val refreshPathSegment = "refresh"
    private val actualAuthContextPathSegment = "context"

    private fun getDefaultClientName(): String = "${Build.MANUFACTURER}-${Build.MODEL}"

    suspend fun register(schema: RegisterSchema): Result<RegisterResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            val requestDto = RegisterRequestDTO(
                firstName = schema.firstName,
                lastName = schema.lastName,
                username = schema.username,
                login = schema.login,
                password = schema.password,
                clientName = getDefaultClientName()
            )

            apiClient.requestDataObject<RegisterResponseDTO> {
                method = HttpMethod.Post
                url { appendPathSegments(authFeaturePathSegment, registerPathSegment) }
                setBody(requestDto)
            }
        }
    }

    suspend fun login(schema: LoginSchema): Result<LoginResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            val requestDto = LoginRequestDTO(
                login = schema.login,
                password = schema.password,
                clientName = getDefaultClientName()
            )

            apiClient.requestDataObject<LoginResponseDTO> {
                method = HttpMethod.Post
                url { appendPathSegments(authFeaturePathSegment, loginPathSegment) }
                setBody(requestDto)
            }
        }
    }

    suspend fun refresh(refreshToken: String): Result<RefreshResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            val requestDto = RefreshRequestDTO(
                refreshToken = refreshToken,
                clientName = getDefaultClientName()
            )

            apiClient.requestDataObject<RefreshResponseDTO> {
                method = HttpMethod.Post
                url { appendPathSegments(authFeaturePathSegment, refreshPathSegment) }
                setBody(requestDto)
            }
        }
    }

    suspend fun getCurrentAuthContext(accessToken: String): Result<GetCurrentAuthContextResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            apiClient.requestDataObject<GetCurrentAuthContextResponseDTO> {
                method = HttpMethod.Get
                url { appendPathSegments(authFeaturePathSegment, actualAuthContextPathSegment) }
                headers {
                    set(HttpHeaders.Authorization, "Bearer $accessToken")
                }
            }
        }
    }
}
