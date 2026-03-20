package com.universall.auth_impl.data.sources

import com.universall.appcore.network.api.ApiClient
import com.universall.auth_api.domain.di.qualifiers.AuthenticatedApiClient
import com.universall.auth_impl.data.dto.get_me.GetMeResponseDTO
import io.ktor.http.HttpMethod
import io.ktor.http.appendPathSegments
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Singleton
class UsersNetworkDataSource @Inject constructor(
    @param:AuthenticatedApiClient private val authenticatedApiClient: ApiClient
) {
    private val usersPathSegment = "users"
    private val getMePathSegment = "me"

    suspend fun getMe(): Result<GetMeResponseDTO> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            authenticatedApiClient.requestDataObject<GetMeResponseDTO> {
                method = HttpMethod.Get
                url { appendPathSegments(usersPathSegment, getMePathSegment) }
            }
        }
    }
}
