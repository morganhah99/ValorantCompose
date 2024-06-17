package com.example.valorantcompose.data.repository.remote

import com.example.valorantcompose.data.model.agents.DataModel
import com.example.valorantcompose.data.remote.AgentService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AgentRepositoryImpl @Inject constructor(
    private val agentService: AgentService
): AgentRepository {

    override suspend fun getAgents(): Flow<List<DataModel>> = flow {
        val response = agentService.getAgents()
        val agentsList = response.data?.filterNotNull() ?: emptyList()
        emit(agentsList)
    }


}