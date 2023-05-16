package com.example.luckypaw.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.luckypaw.LuckyPaw
import com.example.luckypaw.screens.*

fun NavGraphBuilder.navGraph(navController: NavController) {
    navigation(
        route = Graph.AUTH,
        startDestination = LuckyPaw.Starter.name
    ) {
        composable(
            route = LuckyPaw.Starter.name
        ) {
            Starter(navController)
        }
        composable(
            route = LuckyPaw.Login.name
        ) {
            Login(navController)
        }
        composable(
            route = LuckyPaw.Register.name,
        ) {
            Register(navController)
        }
        composable(
            route = LuckyPaw.BottomTab.name,
        ) {
            BottomTabScreen()
        }
    }
}