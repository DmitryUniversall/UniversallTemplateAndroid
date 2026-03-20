package com.universall.server_tools_impl.data.repositories

import com.universall.server_tools_api.domain.repositories.ServerToolsRepository
import com.universall.server_tools_impl.data.sources.ServerToolsNetworkDataSource
import javax.inject.Inject

class ServerToolsRepositoryImpl @Inject constructor(
    private val networkDataSource: ServerToolsNetworkDataSource
) : ServerToolsRepository {
    override suspend fun ping(): Result<Unit> {
        return networkDataSource.ping()
    }
}
