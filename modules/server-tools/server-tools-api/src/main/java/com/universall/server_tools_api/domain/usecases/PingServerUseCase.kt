package com.universall.server_tools_api.domain.usecases

interface PingServerUseCase {
    suspend operator fun invoke(): Result<Unit>
}
