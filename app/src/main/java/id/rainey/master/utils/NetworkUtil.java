package id.rainey.master.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import id.rainey.master.app.Application;

public class NetworkUtil {

    //check whether mobile data or Wi-Fi is connected
    public static boolean isNetworkConnected() {
        Context context = Application.getContext();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }
}
