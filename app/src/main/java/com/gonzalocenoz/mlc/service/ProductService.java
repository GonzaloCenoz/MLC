package com.gonzalocenoz.mlc.service;

import android.util.Log;

import com.gonzalocenoz.mlc.api.*;
import com.gonzalocenoz.mlc.model.productSearch.*;

import retrofit2.Callback;

public class ProductService {

    private static IMeliAPI api = MeliAPI.getMeliAPI();

    public static final int RESPONSE_CODE_OK = 200;
    public static final int RESPONSE_CODE_INTERNAL_SERVER_ERROR = 500;
    public static final int RESPONSE_CODE_NO_CONTENT = 204;
    public static final int RESPONSE_NOT_FOUND = 404;

    public void searchProducts(String query, Callback<ProductSearch> callback) {

        try
        {
            api.searchProducts(query).enqueue(callback);
        }
        catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }

}