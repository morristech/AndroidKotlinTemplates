package com.templates.kotlintemplates.utils.from_auf_

import android.location.Location

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.VisibleRegion

import java.util.Locale

object GoogleMapUtils {

    //100 km radius
    val fixedRadius = 100f

    fun calculateDistance(oldLocation: Location, newLocation: Marker): String {
        val results = FloatArray(1)
        Location.distanceBetween(oldLocation.latitude, oldLocation.longitude, newLocation.getPosition().latitude, newLocation.getPosition().longitude, results)
        return formatDistance(results[0])
    }

    fun calculateDistance(startLatitude: Double, startLongitude: Double, endLatitude: Double, endLongitude: Double): String {
        val results = FloatArray(1)
        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results)
        return formatDistance(results[0])
    }

    private fun formatDistance(meters: Float): String {
        if (meters > 1000) {
            return String.format(Locale.getDefault(), "%.2f", meters / 1000) + "km away"
        } else {
            return String.format(Locale.getDefault(), "%.0f", meters) + "m away"
        }
    }

    fun calculateRadius(googleMap: GoogleMap): Float {
        val visibleRegion = googleMap.getProjection().getVisibleRegion()
        val farRight = visibleRegion.farRight
        val farLeft = visibleRegion.farLeft
        val nearRight = visibleRegion.nearRight
        val nearLeft = visibleRegion.nearLeft

        val distanceWidth = FloatArray(2)

        Location.distanceBetween(
                (farRight.latitude + nearRight.latitude) / 2,
                (farRight.longitude + nearRight.longitude) / 2,
                (farLeft.latitude + nearLeft.latitude) / 2,
                (farLeft.longitude + nearLeft.longitude) / 2,
                distanceWidth)


        val distanceHeight = FloatArray(2)
        Location.distanceBetween(
                (farRight.latitude + nearRight.latitude) / 2,
                (farRight.longitude + nearRight.longitude) / 2,
                (farLeft.latitude + nearLeft.latitude) / 2,
                (farLeft.longitude + nearLeft.longitude) / 2,
                distanceHeight)

        val distance: Float

        if (distanceWidth[0] > distanceHeight[0]) {
            distance = distanceWidth[0]
        } else {
            distance = distanceHeight[0]
        }
        //Return km
        return distance / 1000
    }
}
