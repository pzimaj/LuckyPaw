package com.example.luckypaw.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun Map(latitude: Double, longitude: Double) {
    val dogLocation = com.google.android.gms.maps.model.LatLng(latitude, longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(dogLocation, 10f)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ){
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = dogLocation),
                title = "Dog location",
                snippet = "Marker in Dog Location"
            )
        }
    }
}