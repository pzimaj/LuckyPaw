package com.example.luckypaw.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.luckypaw.LuckyPaw
import com.example.luckypaw.map.Map
import com.example.luckypaw.models.Dog
import com.example.luckypaw.utils.fetchDbInfo

@SuppressLint("UnrememberedMutableState")
@Composable
fun DogDetailsScreen(dogName: String, navController: NavController) {
    val dogList: SnapshotStateList<Dog?>
    val context = LocalContext.current

    dogList = fetchDbInfo(dogName, context)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            itemsIndexed(dogList) { _, item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                ) {
                    AsyncImage(
                        model = item?.imageURL,
                        contentDescription = item?.image,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.size(30.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(350.dp)
                            .height(100.dp)
                            .shadow(
                                16.dp,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .background(
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colors.background
                            )
                            .padding(start = 10.dp),

                        ) {
                        Row {
                            Column(
                                modifier = Modifier
                                    .padding(start = 3.dp, top = 12.dp)
                            ) {
                                item?.name?.let {
                                    Text(
                                        text = it,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 30.sp,
                                    )
                                }
                                Text(text = item?.breed + " - " + item?.age)
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 25.dp, end = 5.dp),
                                horizontalAlignment = Alignment.End
                            ) {
                                if (item?.gender == "m") {
                                    Image(
                                        painter = painterResource(id = com.example.luckypaw.R.drawable.male),
                                        contentDescription = "Male icon",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                } else {
                                    Image(
                                        painter = painterResource(id = com.example.luckypaw.R.drawable.female),
                                        contentDescription = "Male icon",
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier.size(50.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(30.dp))

                Text(
                    text = "About " + item?.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.size(20.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        Modifier
                            .background(
                                color = MaterialTheme.colors.secondary,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .size(80.dp)
                            .padding(10.dp)
                    ) {
                        Column {
                            Text(text = "Weight")
                            item?.weight?.let {
                                Text(
                                    text = it,
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(50.dp))

                    Box(
                        Modifier
                            .background(
                                color = MaterialTheme.colors.secondary,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .size(80.dp)
                            .padding(10.dp)
                    ) {
                        Column {
                            Text(text = "Height")
                            item?.height?.let {
                                Text(
                                    text = it,
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.size(50.dp))

                    Box(
                        Modifier
                            .background(
                                color = MaterialTheme.colors.secondary,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .size(80.dp)
                            .padding(10.dp)
                    ) {
                        Column {
                            Text(text = "Color")
                            item?.color?.let {
                                Text(
                                    text = it,
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(30.dp))

                Text(
                    text = item?.name + " Behavior",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.size(20.dp))

                item?.behavior?.forEach {
                    Row(
                        modifier = Modifier.padding(start = 20.dp)
                    ) {
                        Icon(Icons.Rounded.ArrowForward, contentDescription = "arrow")
                        Spacer(modifier = Modifier.size(1.dp))
                        Text(text = it)
                    }
                }
                Spacer(modifier = Modifier.size(30.dp))

                Text(
                    text = item?.name + "'s Location",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )

                Spacer(modifier = Modifier.size(20.dp))

                item?.longitude?.let { item.latitude?.let { it1 -> Map(latitude = it1.toDouble(), longitude = it.toDouble()) } }

                Spacer(modifier = Modifier.size(30.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            navController.navigate(LuckyPaw.Meeting.name + "/${item?.name}")
                        }
                    ) {
                        Text(text = "Click here to schedule a meeting!")
                    }
                }

                Spacer(modifier = Modifier.size(60.dp))
            }
        }
    }
}
