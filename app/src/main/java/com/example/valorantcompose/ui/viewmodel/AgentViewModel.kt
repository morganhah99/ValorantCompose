package com.example.valorantcompose.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorantcompose.data.model.agents.DataModel
import com.example.valorantcompose.data.repository.AgentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(
    private val agentRepository: AgentRepository
): ViewModel() {

    private val _agents = MutableStateFlow<List<DataModel>>(emptyList())
    val agents: StateFlow<List<DataModel>> get() = _agents

    init {
        fetchAgents()
    }

    private fun fetchAgents() {
        viewModelScope.launch(Dispatchers.IO) {
            agentRepository.getAgents()
                .collect { agentList ->
                    _agents.value = agentList
                }
        }
    }
}