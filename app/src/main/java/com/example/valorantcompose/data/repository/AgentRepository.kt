package com.example.valorantcompose.data.repository

import com.example.valorantcompose.data.model.agents.DataModel
import kotlinx.coroutines.flow.Flow

interface AgentRepository {

    suspend fun getAgents(): Flow<List<DataModel>>
}