package com.audit.health.ssc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionChecker {

    //this method is checking internet conneted or not
    public static Boolean isConnectingToInternet(Context context) {
        Boolean activeOrNot = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            activeOrNot = true;
        } else {
            activeOrNot = false;
        }
        return activeOrNot;
    }

}