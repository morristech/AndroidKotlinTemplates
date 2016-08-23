package com.templates.kotlintemplates.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworcUtils {

    /**
     * Checks if the Internet connection is available.
     *
     * @return Returns true if the Internet connection is available. False otherwise.
     * *
     */
    public static boolean isInternetAvailable(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if network is NOT available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo!= null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
    /**
     * Checks the type of data connection that is currently available on the device.
     *
     * @return <code>ConnectivityManager.TYPE_*</code> as a type of internet connection on the
     * device. Returns -1 in case of error or none of
     * <code>ConnectivityManager.TYPE_*</code> is found.
     * **
     */
    public static int getDataConnectionType(Context ctx) {
        ConnectivityManager connMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr != null && connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
            if (connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return ConnectivityManager.TYPE_MOBILE;
            } else if (connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return ConnectivityManager.TYPE_WIFI;
            } else
                return -1;
        } else
            return -1;
    }
}
