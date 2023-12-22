package com.wisnumkt.capstone1.ui.maps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.wisnumkt.capstone1.componen.TopMaps
import com.wisnumkt.capstone1.ui.theme.Capstone1Theme

@Composable
fun rememberMapViewWithLifecycle(context: Context): MapView {
    val mapView = remember { MapView(context) }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose { lifecycleOwner.lifecycle.removeObserver(lifecycleObserver) }
    }

    return mapView
}

@Composable
fun MapsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        TopMaps()

        val context = LocalContext.current
        val mapView = rememberMapViewWithLifecycle(context)
        AndroidView(
            factory = { mapView },
        ) { mapView ->
            mapView.getMapAsync { googleMap ->
                googleMap.uiSettings.isZoomControlsEnabled = true
                googleMap.uiSettings.isIndoorLevelPickerEnabled = true
                googleMap.uiSettings.isCompassEnabled = true
                googleMap.uiSettings.isMapToolbarEnabled = true

                // Check location permission
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                    googleMap.isMyLocationEnabled = true
                    googleMap.uiSettings.isMyLocationButtonEnabled = true


                    // Get current location
                    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
                    fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                        if (location != null) {
                            val userLocation = LatLng(location.latitude, location.longitude)
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 15f))
                        }
                    }
                }

                // Coordinates for Surabaya, Indonesia
                val surabaya = LatLng(-7.250445, 112.768845)
                val cameraPosition = CameraPosition.Builder()
                    .target(surabaya)
                    .zoom(12f) // Adjust the zoom level
                    .build()

                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapsScreenPreview() {
    Capstone1Theme {
        MapsScreen()
    }
}