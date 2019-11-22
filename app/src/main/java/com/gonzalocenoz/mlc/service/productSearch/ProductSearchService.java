package com.gonzalocenoz.mlc.service.productSearch;

import android.util.Log;

import com.gonzalocenoz.mlc.api.IMeliAPI;
import com.gonzalocenoz.mlc.api.MeliAPI;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearch;

import retrofit2.Callback;

public class ProductSearchService {

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

}
