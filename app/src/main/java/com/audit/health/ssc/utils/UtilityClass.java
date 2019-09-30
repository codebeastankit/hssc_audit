package com.audit.health.ssc.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.audit.health.ssc.BuildConfig;

public class UtilityClass {
    private static ProgressDialog progressDialog;

    public static void logger(String Tag, String message) {
        if (BuildConfig.DEBUG)
            Log.e(Tag, message);
    }

    public static ProgressDialog showProgress(Context mContext, String message, String title) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
            progressDialog.show();

        }
        return progressDialog;

    }

    public static void dismissProgress(Context mContext) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
        }
        progressDialog.dismiss();
    }

    public static String getCurrentDateTime() {
        Calendar cal1 = Calendar.getInstance(); // creates calendar
        cal1.setTime(new Date()); // sets calendar time/date
        Date b = cal1.getTime();
        SimpleDateFormat foramtter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
        String dateStart = foramtter.format(b);
        return dateStart;
    }

}
