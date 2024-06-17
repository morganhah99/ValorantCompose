package com.example.valorantcompose.data.remote

import com.example.valorantcompose.data.model.agents.AgentModel
import retrofit2.http.GET

interface ValorantService {

    @GET(ValorantServiceDetails.AGENTS_ENDPOINT)
    suspend fun getAgents(): AgentModel
}