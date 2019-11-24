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

    public String FormatCondition(String condition) {
        return condition.equals("new")?"Producto nuevo":"Producto usado";
    }

    public String FormatPrice(String currencyId, Double price) {
        return currencyId + " " + String.format("%,.2f", price);
    }

    public String FormatAcceptsMP(Boolean amp) {
        return String.format("Acepta MercadoPago : %s" , amp?"Si":"No");
    }

    public String FormatAvailableQuatity(Integer aq) {
        return String.format("Cantidad disponible : %d " , aq);
    }


}
