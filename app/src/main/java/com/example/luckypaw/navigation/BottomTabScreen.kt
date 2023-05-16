package com.example.luckypaw.navigation

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomTabScreen() {
    val navController2 = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController2)
        }
    ) {
        BottomNavGraph(navController = navController2)
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val appScreens = listOf(
        BottomTabNavigation.Home,
        BottomTabNavigation.About,
        BottomTabNavigation.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        appScreens.forEach {
            BottomNavigationItem(
                label = {
                    Text(it.title)
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = "Bottom Tab Navigation Icon"
                    )
                },
                selected = currentDestination?.hierarchy?.any {
                    it.route == it.route
                } == true,
                onClick = {
                    navController.navigate(it.route)
                }
            )
        }
    }
}