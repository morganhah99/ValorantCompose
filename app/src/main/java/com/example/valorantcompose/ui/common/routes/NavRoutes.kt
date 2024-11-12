package com.example.valorantcompose.ui.common.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavRoutes(
    val route: String,
    val icon: ImageVector? = null,
    val label: String
) {
    object AgentList: NavRoutes(route =  "agentList", Icons.Default.Info, "Agents")
    object AgentDetails: NavRoutes(route = "agentDetails", null, "")
    object EsportNewsHome: NavRoutes(route = "esportNewsHome", Icons.Default.Home, "Esport News")

}