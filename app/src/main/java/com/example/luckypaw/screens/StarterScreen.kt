package com.example.luckypaw.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.luckypaw.LuckyPaw
import com.example.luckypaw.R

@Composable
fun Starter(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
            .padding(45.dp)
    ) {
        Text(
            text = "Make a new friend with Lucky Paw!",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.background,
        )

        Text(
            text = "Adopt, don't shop",
            color = MaterialTheme.colors.background,
        )
        
        Spacer(modifier = Modifier.size(50.dp))
        
        Image(
            painterResource(R.drawable.paws),
            contentDescription = "Paw image",
            modifier = Modifier.size(350.dp)
        )

        Spacer(modifier = Modifier.size(50.dp))

        Button(
            onClick = {
                navController.navigate(LuckyPaw.Login.name)
            }
        ) {
            Text(text = "Click here to enter!")
        }
    }
}