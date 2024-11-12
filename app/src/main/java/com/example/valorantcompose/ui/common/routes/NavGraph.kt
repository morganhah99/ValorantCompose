package com.example.valorantcompose.ui.common.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.valorantcompose.ui.composable.AgentDetailsScreen
import com.example.valorantcompose.ui.composable.AgentListScreen
import com.example.valorantcompose.ui.composable.EsportNewsScreen
import com.example.valorantcompose.ui.viewmodel.AgentViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    agentViewModel: AgentViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.EsportNewsHome.route
    ) {
        composable(
            route = NavRoutes.AgentList.route
        ) {
            AgentListScreen(agentViewModel, navController = navController)
        }
        composable(
            route = NavRoutes.AgentDetails.route
        ) {
            AgentDetailsScreen(agentViewModel = agentViewModel)
        }
        composable(
            route = NavRoutes.EsportNewsHome.route
        ) {
            EsportNewsScreen(navController = navController)
        }

    }
}