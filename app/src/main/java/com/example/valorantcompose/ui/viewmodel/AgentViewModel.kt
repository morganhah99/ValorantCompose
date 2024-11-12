package com.example.valorantcompose.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantcompose.data.model.agents.DataModel
import com.example.valorantcompose.data.repository.local.AgentDataStoreRepository
import com.example.valorantcompose.data.repository.remote.AgentRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(
    private val agentRepository: AgentRepository,
    private val agentDataStoreRepository: AgentDataStoreRepository
): ViewModel() {

    private val _agents = MutableStateFlow<List<DataModel>>(emptyList())
    val agents: StateFlow<List<DataModel>> get() = _agents

    private val _selectedAgent = MutableStateFlow<DataModel?>(null)
    val selectedAgent: StateFlow<DataModel?> get() = _selectedAgent

    init {
        fetchAgents()
    }

    private fun fetchAgents() {
        viewModelScope.launch {
            agentDataStoreRepository.getAgents().collect { agentsJson ->
                if (agentsJson.isNullOrEmpty()) {
                    val fetchedAgents = agentRepository.getAgents().first()
                    val agentsList = fetchedAgents
                    _agents.value = agentsList
                    saveAgentsToCache(agentsList)
                } else {
                    Log.d("LOCAL", "PULLING DATA FROM LOCAL STORAGE")
                    val agentsList = parseAgentsFromJson(agentsJson)
                    _agents.value = agentsList
                }
            }
        }
    }

    fun selectAgent(agent: DataModel) {
        _selectedAgent.value = agent
    }

    private suspend fun saveAgentsToCache(agents: List<DataModel>) {
        val agentsJson = convertAgentsToJson(agents)
        agentDataStoreRepository.saveAgents(agentsJson)
    }

    private fun convertAgentsToJson(agents: List<DataModel>): String {
        return Gson().toJson(agents)
    }

    private fun parseAgentsFromJson(agentsJson: String): List<DataModel> {
        val listType = object : TypeToken<List<DataModel>>() {}.type
        return Gson().fromJson(agentsJson, listType)
    }
}