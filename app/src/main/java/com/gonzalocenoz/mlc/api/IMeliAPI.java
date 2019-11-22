package com.gonzalocenoz.mlc.api;

import com.gonzalocenoz.mlc.model.productSearch.ProductSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMeliAPI {

    public static final int RESPONSE_OK_CODE = 200;

    @GET("sites/MLA/search")
    Call<ProductSearch> searchProducts(@Query("q") String query);

}
