package com.example.valorantcompose.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.valorantcompose.data.model.agents.AbilityModel
import com.example.valorantcompose.ui.viewmodel.AgentViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun AgentDetailsScreen(agentViewModel: AgentViewModel = hiltViewModel()) {
    val agent by agentViewModel.selectedAgent.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = rememberAsyncImagePainter(agent?.displayIcon),
            modifier = Modifier.size(64.dp),
            contentDescription = null,
        )
        Text(text = agent?.displayName ?: "Agent Name")
        Spacer(modifier = Modifier.height(8.dp))

        // Display the abilities header
        agent?.let {
            Spacer(modifier = Modifier.height(8.dp))

            AbilitiesGrid(abilities = sortAbilities(it.abilities ?: emptyList()))
        } ?: Text(text = "No agent selected")
    }
}

fun sortAbilities(abilities: List<AbilityModel?>): List<AbilityModel?> {
    val order = listOf("Q", "E", "C", "X")
    return abilities.sortedBy { ability ->
        order.indexOf(ability?.slot ?: "")
    }
}

@Composable
fun AbilitiesGrid(abilities: List<AbilityModel?>) {
    Column {
        for (i in abilities.indices step 2) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AbilityItem(ability = abilities[i] ?: AbilityModel())
                if (i + 1 < abilities.size) {
                    AbilityItem(ability = abilities[i + 1] ?: AbilityModel())
                } else {
                    Spacer(modifier = Modifier.weight(1f)) // Empty space if there's no second item
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun AbilityItem(ability: AbilityModel) {
    Column(modifier = Modifier
        .padding(4.dp)
        ) {
        ability.displayIcon?.let { iconUrl ->
            Image(
                painter = rememberAsyncImagePainter(iconUrl),
                contentDescription = "${ability.displayName} icon",
                modifier = Modifier.size(48.dp),
                colorFilter = ColorFilter.tint(Color.Black) // Apply a black tint
            )
        }
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = ability.displayName ?: "Unknown Ability")
    }
}
