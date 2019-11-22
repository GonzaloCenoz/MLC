package com.gonzalocenoz.mlc.api;

import com.gonzalocenoz.mlc.model.productSearch.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMeliAPI {


    @GET("sites/MLA/search")
    Call<ProductSearch> searchProducts(@Query("q") String query);

}