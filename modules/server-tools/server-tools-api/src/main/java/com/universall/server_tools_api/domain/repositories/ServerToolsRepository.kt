package com.universall.server_tools_api.domain.repositories

interface ServerToolsRepository {
    suspend fun ping(): Result<Unit>
}
