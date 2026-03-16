package com.universall.auth_impl.data.sources

import android.os.Build
import com.universall.appcore.network.api.CoreApiClient
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
import io.ktor.http.path
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthNetworkDataSource(  // TODO: Handle error app codes
    private val apiClient: CoreApiClient
) {
    private val authFeaturePath = "/auth"

    private val registerPath = "$authFeaturePath/register"
    private val loginPath = "$authFeaturePath/login"
    private val refreshPath = "$authFeaturePath/refresh"
    private val fetchActualAuthContextPath = "$authFeaturePath/context"

    private fun getDefaultClientName(): String = "${Build.MANUFACTURER}-${Build.MODEL}"

    suspend fun register(schema: RegisterSchema): Result<RegisterResponseDTO> = withContext(Dispatchers.IO) {
        val requestDto = RegisterRequestDTO(
            firstName = schema.firstName,
            lastName = schema.lastName,
            username = schema.username,
            login = schema.login,
            password = schema.password,
            clientName = getDefaultClientName()
        )

        return@withContext runCatching {
            apiClient.requestDataNotNull<RegisterResponseDTO> {
                method = HttpMethod.Post
                url { path(registerPath) }
                setBody(requestDto)
            }
        }
    }

    suspend fun login(schema: LoginSchema): Result<LoginResponseDTO> = withContext(Dispatchers.IO) {
        val requestDto = LoginRequestDTO(
            login = schema.login,
            password = schema.password,
            clientName = getDefaultClientName()
        )

        return@withContext runCatching {
            apiClient.requestDataNotNull<LoginResponseDTO> {
                method = HttpMethod.Post
                url { path(loginPath) }
                setBody(requestDto)
            }
        }
    }

    suspend fun refresh(refreshToken: String): Result<RefreshResponseDTO> = withContext(Dispatchers.IO) {
        val requestDto = RefreshRequestDTO(
            refreshToken = refreshToken,
            clientName = getDefaultClientName()
        )

        return@withContext runCatching {
            apiClient.requestDataNotNull<RefreshResponseDTO> {
                method = HttpMethod.Post
                url { path(refreshPath) }
                setBody(requestDto)
            }
        }
    }

    suspend fun getCurrentAuthContext(accessToken: String): Result<GetCurrentAuthContextResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            apiClient.requestDataNotNull<GetCurrentAuthContextResponseDTO> {
                method = HttpMethod.Get
                url { path(fetchActualAuthContextPath) }
                headers {
                    set(HttpHeaders.Authorization, "Bearer $accessToken")
                }
            }
        }
    }
}
