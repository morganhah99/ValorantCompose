package com.example.valorantcompose.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.valorantcompose.data.model.agents.DataModel
import com.example.valorantcompose.ui.routes.NavRoutes
import com.example.valorantcompose.ui.viewmodel.AgentViewModel

@Composable
fun AgentListScreen(
    agentViewModel: AgentViewModel = hiltViewModel(),
    navController: NavController
) {
    val agents by agentViewModel.agents.collectAsState()

    AgentList(agents = agents, navController = navController)
}

@Composable
fun AgentList(agents: List<DataModel>, navController: NavController) {
    LazyColumn {
        items(agents) { agent ->
            AgentListItem(agent = agent, navController)
        }
    }
}



@Composable
fun AgentListItem(agent: DataModel, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(route = NavRoutes.AgentDetails.route)
            }
            .fillMaxWidth()
            .padding(8.dp),
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
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "${agent.displayName}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}