package com.example.valorantcompose.ui.routes

sealed class NavRoutes(
    val route: String
) {
    object AgentList: NavRoutes(route =  "agentList")
    object AgentDetails: NavRoutes(route = "agentDetails")

}