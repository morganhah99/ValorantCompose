package com.example.valorantcompose.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.valorantcompose.data.model.agents.DataModel
import com.example.valorantcompose.ui.common.BottomNavigationBar
import com.example.valorantcompose.ui.common.routes.NavRoutes
import com.example.valorantcompose.ui.viewmodel.AgentViewModel

@Composable
fun AgentListScreen(
    agentViewModel: AgentViewModel = hiltViewModel(),
    navController: NavController
) {
    val agents by agentViewModel.agents.collectAsState()

    AgentList(agents = agents, navController = navController, agentViewModel)
}

@Composable
fun AgentList(
    agents: List<DataModel>,
    navController: NavController,
    agentViewModel: AgentViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1923))
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(agents) { agent ->
                AgentListItem(agent = agent, navController, agentViewModel = agentViewModel)
            }
        }
        BottomNavigationBar(navController)
    }
}


@Composable
fun AgentListItem(agent: DataModel, navController: NavController, agentViewModel: AgentViewModel) {
    Surface(
        modifier = Modifier
            .clickable {
                agentViewModel.selectAgent(agent)
                navController.navigate(route = NavRoutes.AgentDetails.route)
            }
            .fillMaxWidth(),
        color = Color(0xFF1A1A1A)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(agent.displayIcon),
                modifier = Modifier.size(64.dp),
                contentDescription = null,
            )
            Column {
                Text(
                    text = "${agent.displayName}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }
        }
    }
}