package com.example.luckypaw.utils

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.luckypaw.models.Dog
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

fun fetchDbInfo(dogName: String, context: Context): SnapshotStateList<Dog?> {

    val dogList = mutableStateListOf<Dog?>()
    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    val dogRef = db.collection("dogs")

    dogRef.whereEqualTo("name", dogName).get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                val documentSnapshot: DocumentSnapshot = document
                dogList.add(
                    Dog (
                        name = documentSnapshot.getString("name") ?: "",
                        imageURL = documentSnapshot.getString("imageURL") ?: "",
                        image = documentSnapshot.getString("image") ?: "",
                        breed = documentSnapshot.getString("breed") ?: "",
                        age = documentSnapshot.getString("age") ?: "",
                        gender = documentSnapshot.getString("gender") ?: "",
                        availability = documentSnapshot.getString("availability") ?: "",
                        weight = documentSnapshot.getString("weight") ?: "",
                        height = documentSnapshot.getString("height") ?: "",
                        color = documentSnapshot.getString("color") ?: "",
                        behavior = documentSnapshot.get("behavior") as List<String>?,
                        longitude = documentSnapshot.getString("longitude") ?: "",
                        latitude = documentSnapshot.getString("latitude") ?: ""
                    )
                )
            }
        }
        .addOnFailureListener {
            Toast.makeText(
                context,
                "Failed to load data",
                Toast.LENGTH_SHORT
            ).show()
        }

    return dogList
}