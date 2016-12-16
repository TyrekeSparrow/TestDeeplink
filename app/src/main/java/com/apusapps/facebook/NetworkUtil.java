
package com.apusapps.facebook;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil {

    public static boolean isNetworkAvailable(Context context) {
        if (context != null) {
            final ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable() && info.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWifiActive(Context context) {
        if (context != null) {
            final ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Service.CONNECTIVITY_SERVICE);
            final NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWifiAvailable(Context context) {
        final boolean isWifiActive = isWifiActive(context);
        final boolean isNetworkAvailable = isNetworkAvailable(context);
        final boolean isWifiAvailable = isWifiActive && isNetworkAvailable;
        return isWifiAvailable;
    }
}
