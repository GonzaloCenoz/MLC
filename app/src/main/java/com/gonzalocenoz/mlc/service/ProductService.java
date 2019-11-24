package com.gonzalocenoz.mlc.service;

import android.util.Log;

import com.gonzalocenoz.mlc.api.*;
import com.gonzalocenoz.mlc.model.productDetail.ProductDetail;
import com.gonzalocenoz.mlc.model.productSearch.*;

import retrofit2.Callback;

public class ProductService {

    private static IMeliAPI api = MeliAPI.getMeliAPI();



    public void searchProducts(String query, Callback<ProductSearch> callback) {

        try
        {
            api.searchProducts(query).enqueue(callback);
        }
        catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }

    public void getProductDetail(String productId, Callback<ProductDetail> callback) {

        try
        {
            api.getProductDetail(productId).enqueue(callback);
        }
        catch (Exception e){
            Log.e("ERROR",e.getMessage());
        }

    }
}