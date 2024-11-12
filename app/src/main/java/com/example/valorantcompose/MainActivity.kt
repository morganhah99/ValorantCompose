package com.example.valorantcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.valorantcompose.ui.common.routes.SetupNavGraph
import com.example.valorantcompose.ui.common.theme.ValorantComposeTheme
import com.example.valorantcompose.ui.viewmodel.AgentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantComposeTheme {
                val agentViewModel: AgentViewModel = hiltViewModel()
                navController = rememberNavController()
                SetupNavGraph(agentViewModel = agentViewModel, navController = navController)
            }
        }
    }
}

