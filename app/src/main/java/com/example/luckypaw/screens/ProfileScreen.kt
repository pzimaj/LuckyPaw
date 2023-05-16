package com.example.luckypaw.screens

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.luckypaw.components.DogCard
import com.example.luckypaw.components.EmptyCard
import com.example.luckypaw.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileScreen() {
    val userList = mutableStateListOf<User?>()
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val user = Firebase.auth.currentUser
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    if (user != null) {
        db.collection("users").document(user.uid)
            .get()
            .addOnSuccessListener {
                val documentSnapshot: DocumentSnapshot = it
                userList.add(
                    User(
                        name = documentSnapshot.getString("name") ?: "",
                        surname = documentSnapshot.getString("surname") ?: "",
                        dogs = documentSnapshot.get("dogs") as List<String>?,
                    )
                )
            }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(userList) { _, item ->

                Spacer(modifier = Modifier.size(20.dp))

                item?.name?.let {
                    Text(
                        text = it + " " + item.surname,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .width(500.dp)
                            .padding(start = 10.dp),
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.LightGray)
                )

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Scheduled to visit",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Start,
                    color = Color.LightGray,
                    modifier = Modifier
                        .width(500.dp)
                        .padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.size(20.dp))

                if (item?.dogs == null) {
                    EmptyCard()
                }

                if (item != null) {
                    item.dogs?.get(0)?.let { DogCard(dogName = it, context) }
                }

                Spacer(modifier = Modifier.size(20.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            auth.signOut()

                            //Code used for restarting the app
                            val packageManager: PackageManager = context.packageManager
                            val intent: Intent = packageManager.getLaunchIntentForPackage(context.packageName)!!
                            val componentName: ComponentName = intent.component!!
                            val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
                            context.startActivity(restartIntent)
                            Runtime.getRuntime().exit(0)
                        }
                    ) {
                        Text(text = "Log out")
                    }
                }
            }
        }
    }
}
