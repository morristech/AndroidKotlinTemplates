package com.templates.kotlintemplates.utils.from_auf_;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.net.ConnectivityManager.TYPE_MOBILE;
import static android.net.ConnectivityManager.TYPE_WIFI;

public class NetworkDetector {

    private final NetworkInfo networkInfo;

    public NetworkDetector(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
    }

    public boolean isOnline() {
        return isWifi() || isMobile();
    }

    private boolean isOnlineNetwork(int type) {
        boolean connected = false;
        if (networkInfo != null) {
            if (networkInfo.getType() == type) {
                connected = networkInfo.isAvailable() && networkInfo.isConnected();
            }
        }
        return connected;
    }

    public boolean isMobile() {
        return isOnlineNetwork(TYPE_MOBILE);
    }

    public boolean isWifi() {
        return isOnlineNetwork(TYPE_WIFI);
    }
}
