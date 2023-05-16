package com.example.luckypaw.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyCard() {
    Box(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
    ) {
        Box(
            modifier = Modifier
                .height(350.dp)
                .width(350.dp)
                .shadow(
                    16.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colors.background
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "There is currently no scheduled visit",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start,
                    color = Color.LightGray,
                    modifier = Modifier
                        .width(500.dp)
                        .padding(start = 10.dp)
                )
            }
        }
    }
}