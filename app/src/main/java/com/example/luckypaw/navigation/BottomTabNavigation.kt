package com.example.luckypaw.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTabNavigation(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home: BottomTabNavigation(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object About: BottomTabNavigation(
        route = "about",
        title = "About",
        icon = Icons.Default.List
    )
    object Profile: BottomTabNavigation(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
}
