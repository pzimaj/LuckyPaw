package com.example.luckypaw.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        RootNavigation(navController = navController)
    }
}

@Composable
fun RootNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTH,
    ) {
        navGraph(navController)
        composable(route = Graph.BOTTOM) {
            BottomTabScreen()
        }
    }
}