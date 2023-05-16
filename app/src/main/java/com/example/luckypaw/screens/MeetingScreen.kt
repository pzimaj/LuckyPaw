package com.example.luckypaw.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
import com.example.luckypaw.R
import com.example.luckypaw.models.Dog
import com.example.luckypaw.navigation.BottomTabNavigation
import com.example.luckypaw.utils.fetchDbInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@SuppressLint("UnrememberedMutableState")
@Composable
fun MeetingScreen (dogName: String, navController: NavController) {
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val dogList: SnapshotStateList<Dog?>
    val context = LocalContext.current
    val dogPicked = mutableListOf<String>()
    val user = Firebase.auth.currentUser

    dogList = fetchDbInfo(dogName, context)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            itemsIndexed(dogList) { _, item ->
                Box(
                    modifier = Modifier
                        .height(500.dp)
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
                                model = item?.imageURL,
                                contentDescription = item?.image,
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
                            item?.availability?.let {
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

                Spacer(modifier = Modifier.size(30.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            dogPicked.add(item?.name.toString())

                            if (user != null) {
                                db.collection("users").document(user.uid)
                                    .update("dogs", dogPicked)
                                    .addOnSuccessListener {
                                        Toast.makeText(
                                            context,
                                            "Successfully scheduled a meeting",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(
                                            context,
                                            "There was an issue with scheduling",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                            }

                            navController.navigate(BottomTabNavigation.Home.route) {
                                popUpTo(navController.graph.id) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Text(text = "Schedule a meeting!")
                    }
                }
            }
        }
    }
}