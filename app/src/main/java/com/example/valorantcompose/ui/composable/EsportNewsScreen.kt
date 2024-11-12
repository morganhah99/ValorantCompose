package com.example.valorantcompose.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.valorantcompose.ui.common.BottomNavigationBar

@Composable
fun EsportNewsScreen(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Text("Hello world")
        BottomNavigationBar(navController)
    }
}