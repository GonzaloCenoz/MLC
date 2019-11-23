package com.gonzalocenoz.mlc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gonzalocenoz.mlc.view.MLCApplication;

public class Utils {


    private static Utils utils;

    public static Utils getInstance(){
        if (utils == null)
            utils = new Utils();
        return utils;
    }

    public boolean isInternetAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) MLCApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
