package com.dsdmsa.weather.utils

import android.content.Context
import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

fun GoogleMap.animCamera(location: Location, zoom: Float = 10f) {
    val position = CameraPosition.builder()
            .target(LatLng(location.latitude, location.longitude))
            .zoom(zoom)
            .build()
    this.animateCamera(CameraUpdateFactory.newCameraPosition(position), null)
}

fun GoogleMap.animCameraLatLang(location: LatLng?, zoom: Float = 10f) {
    val position = CameraPosition.builder()
            .target(location)
            .zoom(zoom)
            .build()
    this.animateCamera(CameraUpdateFactory.newCameraPosition(position), null)
}

fun GoogleMap.addInteresUserMarker(context: Context, location: Location) {
    this.addMarker(MarkerOptions()
            .position(LatLng(location.latitude, location.longitude))
            .title("you are here")
    )
}

fun GoogleMap.addPlace(location: LatLng) {
    this.addMarker(MarkerOptions()
            .position(location)
            .title("place")
    )
}