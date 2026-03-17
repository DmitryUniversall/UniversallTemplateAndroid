package com.universall.auth_impl.data.sources

import com.universall.auth_api.domain.di.qualifiers.AuthenticatedApiClient
import com.universall.auth_impl.data.dto.get_me.GetMeResponseDTO
import com.universall.core.network.api.CoreApiClient
import io.ktor.http.HttpMethod
import io.ktor.http.path
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class UsersNetworkDataSource @Inject constructor(
    @param:AuthenticatedApiClient private val authenticatedApiClient: CoreApiClient
) {
    suspend fun fetchMe(): Result<GetMeResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            authenticatedApiClient.requestDataNotNull<GetMeResponseDTO> {
                method = HttpMethod.Get
                url { path("/users/me") }
            }
        }
    }
}
