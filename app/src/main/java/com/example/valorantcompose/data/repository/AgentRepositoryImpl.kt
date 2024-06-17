package com.example.valorantcompose.data.repository

import com.example.valorantcompose.data.model.agents.DataModel
import com.example.valorantcompose.data.remote.ValorantService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AgentRepositoryImpl @Inject constructor(
    private val valorantService: ValorantService
): AgentRepository {

    override suspend fun getAgents(): Flow<List<DataModel>> = flow {
        val response = valorantService.getAgents()
        val agentsList = response.data?.filterNotNull() ?: emptyList()
        emit(agentsList)
    }


}