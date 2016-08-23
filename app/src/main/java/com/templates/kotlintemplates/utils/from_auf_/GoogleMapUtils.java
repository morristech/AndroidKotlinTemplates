package com.templates.kotlintemplates.utils.from_auf_;

import android.location.Location;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.VisibleRegion;

import java.util.Locale;

public class GoogleMapUtils {

    //100 km radius
    private static final float FIXED_HOST_RADIUS = 100f;

    public static String calculateDistance(Location oldLocation, Marker newLocation) {
        float[] results = new float[1];
        Location.distanceBetween(oldLocation.getLatitude(), oldLocation.getLongitude(), newLocation.getPosition().latitude, newLocation.getPosition().longitude, results);
        return formatDistance(results[0]);
    }

    public static String calculateDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        float[] results = new float[1];
        Location.distanceBetween(startLatitude, startLongitude, endLatitude, endLongitude, results);
        return formatDistance(results[0]);
    }

    private static String formatDistance(float meters) {
        if (meters > 1000) {
            return String.format(Locale.getDefault(), "%.2f", meters / 1000) + "km away";
        } else {
            return String.format(Locale.getDefault(), "%.0f", meters) + "m away";
        }
    }

    public static float calculateRadius(GoogleMap googleMap) {
        VisibleRegion visibleRegion = googleMap.getProjection().getVisibleRegion();
        LatLng farRight = visibleRegion.farRight;
        LatLng farLeft = visibleRegion.farLeft;
        LatLng nearRight = visibleRegion.nearRight;
        LatLng nearLeft = visibleRegion.nearLeft;

        float[] distanceWidth = new float[2];

        Location.distanceBetween(
                (farRight.latitude + nearRight.latitude) / 2,
                (farRight.longitude + nearRight.longitude) / 2,
                (farLeft.latitude + nearLeft.latitude) / 2,
                (farLeft.longitude + nearLeft.longitude) / 2,
                distanceWidth
        );


        float[] distanceHeight = new float[2];
        Location.distanceBetween(
                (farRight.latitude + nearRight.latitude) / 2,
                (farRight.longitude + nearRight.longitude) / 2,
                (farLeft.latitude + nearLeft.latitude) / 2,
                (farLeft.longitude + nearLeft.longitude) / 2,
                distanceHeight
        );

        float distance;

        if (distanceWidth[0] > distanceHeight[0]) {
            distance = distanceWidth[0];
        } else {
            distance = distanceHeight[0];
        }
        //Return km
        return distance / 1000;
    }

    public static float getFixedRadius() {

        return FIXED_HOST_RADIUS;
    }
}
