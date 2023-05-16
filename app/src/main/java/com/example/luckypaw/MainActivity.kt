package com.example.luckypaw

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.luckypaw.navigation.Navigation
import com.example.luckypaw.ui.theme.LuckyPawTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LuckyPawTheme {
                val navController = rememberNavController()

                Navigation(navController = navController)
            }
        }
    }
}