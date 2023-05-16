package com.example.luckypaw.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.luckypaw.LuckyPaw
import com.example.luckypaw.screens.*

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.BOTTOM,
        startDestination = BottomTabNavigation.Home.route,
    ) {
        composable(
            route = BottomTabNavigation.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = BottomTabNavigation.About.route
        ) {
            AboutScreen()
        }
        composable(
            route = BottomTabNavigation.Profile.route
        ) {
            ProfileScreen()
        }
        composable(
            route = LuckyPaw.Details.name + "/{dog}"
        ) {
            DogDetailsScreen(dogName = it.arguments?.getString("dog") ?: "", navController)
        }

        composable(
            route = LuckyPaw.Meeting.name + "/{dog}"
        ) {
            MeetingScreen(dogName = it.arguments?.getString("dog") ?: "", navController)
        }
    }
}