package com.example.valorantcompose.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.valorantcompose.data.local.AgentPreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AgentDataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AgentDataStoreRepository {

    override suspend fun saveAgents(agentsJson: String) {
        dataStore.edit { preferences ->
            preferences[AgentPreferencesKeys.AGENT_LIST] = agentsJson
        }
    }

    override fun getAgents(): Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[AgentPreferencesKeys.AGENT_LIST]
        }
}