package com.example.valorantcompose.ui.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.valorantcompose.ui.composable.AgentDetailsScreen
import com.example.valorantcompose.ui.composable.AgentListScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.AgentList.route
    ) {
        composable(
            route = NavRoutes.AgentList.route
        ) {
            AgentListScreen(navController = navController)
        }
        composable(
            route = NavRoutes.AgentDetails.route
        ) {
            AgentDetailsScreen()
        }

    }
}