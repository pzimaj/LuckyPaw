package com.example.luckypaw.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.luckypaw.LuckyPaw
import com.example.luckypaw.models.Dog
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(navController: NavController) {
    val dogList = mutableStateListOf<Dog?>()
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val context = LocalContext.current

    db.collection("dogs").get()
        .addOnSuccessListener {
            if(!it.isEmpty) {
                val dogs = it.documents

                for(d in dogs){
                    val dog: Dog? = d.toObject(Dog::class.java)
                    dogList.add(dog)
                }
            } else {
                Toast.makeText(
                    context,
                    "No data found",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        .addOnFailureListener{
            Toast.makeText(
                context,
                "Failed to load data",
                Toast.LENGTH_SHORT
            ).show()
        }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Explore",
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .width(500.dp)
                .padding(start = 10.dp),
        )
        Text(
            text = "Check out the dogs we have available for adoption",
            textAlign = TextAlign.Start,
            modifier = Modifier
                .width(400.dp)
                .padding(start = 10.dp),
        )

        Spacer(modifier = Modifier.size(20.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .scale(1.01f),
            content = {
                itemsIndexed(dogList) { _, item ->
                    AsyncImage(
                        model = item?.imageURL,
                        contentDescription = item?.image,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(3.dp)
                            .size(120.dp)
                            .clickable {
                                if (item != null) {
                                    navController.navigate(LuckyPaw.Details.name + "/${item.name}")
                                }
                            }
                    )
                }
            }
        )
    }
}