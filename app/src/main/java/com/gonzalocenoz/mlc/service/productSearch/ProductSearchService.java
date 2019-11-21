package com.gonzalocenoz.mlc.service.productSearch;

import com.gonzalocenoz.mlc.api.IMeliAPI;
import com.gonzalocenoz.mlc.api.MeliAPI;

import retrofit2.Callback;

public class ProductSearchService {

    private static IMeliAPI api = MeliAPI.getMeliAPI();

    public void searchProducts(String query, Callback callback) {

        api.searchProducts(query).enqueue(callback);
    }

}
