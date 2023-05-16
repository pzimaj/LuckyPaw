package com.example.luckypaw.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.luckypaw.R
import com.example.luckypaw.models.Dog
import com.example.luckypaw.utils.fetchDbInfo

@SuppressLint("UnrememberedMutableState")
@Composable
fun DogCard(dogName: String, context: Context) {

    val dogList: SnapshotStateList<Dog?> = fetchDbInfo(dogName, context)

    LazyColumn(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp),
    ) {
        itemsIndexed(dogList) {_, dog ->
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
                    Column {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = dog?.imageURL,
                                contentDescription = dog?.image,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(150.dp)
                                    .width(200.dp)
                            )
                        }

                        Spacer(modifier = Modifier.size(20.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .width(350.dp)
                                    .height(100.dp)
                                    .padding(start = 10.dp),
                            ) {
                                Row {
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 3.dp, top = 12.dp)
                                    ) {
                                        dog?.name?.let {
                                            Text(
                                                text = it,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 30.sp,
                                            )
                                        }
                                        Text(text = dog?.breed + " - " + dog?.age)
                                    }

                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 25.dp, end = 5.dp),
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        if (dog?.gender == "m") {
                                            Image(
                                                painter = painterResource(id = R.drawable.male),
                                                contentDescription = "Male icon",
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier.size(50.dp)
                                            )
                                        } else {
                                            Image(
                                                painter = painterResource(id = R.drawable.female),
                                                contentDescription = "Male icon",
                                                contentScale = ContentScale.Fit,
                                                modifier = Modifier.size(50.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.size(20.dp))

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            dog?.availability?.let {
                                Text(
                                    text = "Availability: $it",
                                    color = MaterialTheme.colors.primary,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

        }
    }