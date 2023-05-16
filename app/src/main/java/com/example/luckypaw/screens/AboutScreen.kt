package com.example.luckypaw.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.luckypaw.R

@Composable
fun AboutScreen(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "About LuckyPaw",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .width(500.dp)
                .padding(start = 10.dp),
        )

        Box(
            modifier = Modifier
                .width(500.dp)
                .height(250.dp)
                .padding(20.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column() {
                Text(
                    text = "The excessive popularization of giving dogs as gifts for holidays and birthdays " +
                            "has led to the fact that today, unfortunately, there are more and more paws " +
                            "looking for their forever home. This application was created to make it easy" +
                            "to find the perfect home and forever family.",
                    textAlign = TextAlign.Justify,
                )
                Text(
                    text = "This app was created by Pjeter Zimaj and Marita Kresic. They both love" +
                            "dogs and animals alike and want to spread awareness of the issues that" +
                            "dogs have when finding their forever homes.",
                    textAlign = TextAlign.Justify,
                )
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "Dog image",
                modifier = Modifier
                    .height(600.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}