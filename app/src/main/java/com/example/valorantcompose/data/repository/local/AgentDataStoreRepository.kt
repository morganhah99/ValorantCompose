package com.example.valorantcompose.data.repository.local

import kotlinx.coroutines.flow.Flow

interface AgentDataStoreRepository {

    suspend fun saveAgents(agentsJson: String)

    fun getAgents(): Flow<String?>

}