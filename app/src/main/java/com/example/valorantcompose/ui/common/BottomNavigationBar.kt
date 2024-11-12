package com.example.valorantcompose.ui.common

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.valorantcompose.ui.common.routes.NavRoutes

@Composable
fun BottomNavigationBar(navController: NavController) {
    val bottomNavItems = listOf(
        NavRoutes.EsportNewsHome,
        NavRoutes.AgentList
    )
    BottomNavigation (
        backgroundColor = Color(0xFF0F1923),
        contentColor = Color.White
    ) {
        bottomNavItems.forEach { navRoute ->
            BottomNavigationItem(
                icon = {
                    navRoute.icon?.let { icon ->
                        Icon(imageVector = icon, contentDescription = navRoute.label)
                    }
                },
                label = { Text(navRoute.label) },
                selected = navController.currentBackStackEntry?.destination?.route == navRoute.route,
                onClick = {
                    // Navigate to the selected route
                    navController.navigate(navRoute.route) {
                        // Avoid multiple copies of the same destination in the back stack
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Save the state of the current route
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selectedContentColor = Color(0xFFD1001A),
                unselectedContentColor = Color.Gray
            )
        }
    }

}