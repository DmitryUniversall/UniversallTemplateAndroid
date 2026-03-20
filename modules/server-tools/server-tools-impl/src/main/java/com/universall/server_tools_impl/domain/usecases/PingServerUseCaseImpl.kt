package com.universall.server_tools_impl.domain.usecases

import com.universall.server_tools_api.domain.repositories.ServerToolsRepository
import com.universall.server_tools_api.domain.usecases.PingServerUseCase
import jakarta.inject.Inject

class PingServerUseCaseImpl @Inject constructor(
    private val serverToolsRepository: ServerToolsRepository
) : PingServerUseCase {
    override suspend fun invoke(): Result<Unit> {
        return serverToolsRepository.ping()
    }
}
