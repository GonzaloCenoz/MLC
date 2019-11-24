package com.gonzalocenoz.mlc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.api.MeliAPI;
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

        return condition.equals(MLCApplication.getInstance().getString(R.string.condition_new_attibute))?
                 MLCApplication.getInstance().getString(R.string.condition_new_attibute_formatted):
                 MLCApplication.getInstance().getString(R.string.condition_used_attibute_formatted);
    }

    public String FormatPrice(String currencyId, Double price) {
        return  String.format(MLCApplication.getInstance().getString(R.string.currency_price_attribute_formatted),currencyId,price);
    }

    public String FormatAcceptsMP(Boolean amp) {

        String v = amp?
                MLCApplication.getInstance().getString(R.string.accept_mp_Yes_attribute_formatted):
                MLCApplication.getInstance().getString(R.string.accept_mp_no_attribute_formatted);

        return  String.format(MLCApplication.getInstance().getString(R.string.accept_mp_attribute_formatted), v);
    }

    public String FormatAvailableQuantity(Integer aq) {

        return  String.format(MLCApplication.getInstance().getString(R.string.available_quantity_attribute_formatted),aq);
    }


    public String getErrorMessage(Integer value) {
        String m;

        switch (value)
        {
            case MeliAPI.RESPONSE_CODE_INTERNAL_SERVER_ERROR:

                if(!Utils.getInstance().isInternetAvailable())
                {
                    m = MLCApplication.getInstance().getString(R.string.internetError);
                }
                else
                {
                    m = MLCApplication.getInstance().getString(R.string.internalServerError);
                }

                break;
            case MeliAPI.RESPONSE_CODE_NO_CONTENT:
                m = MLCApplication.getInstance().getString(R.string.noContent);
                break;
            default:
                m =  MLCApplication.getInstance().getString(R.string.genericError) + value.toString();
                break;
        }
        return m;
    }
}
