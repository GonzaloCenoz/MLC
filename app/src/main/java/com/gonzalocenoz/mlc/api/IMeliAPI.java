package com.gonzalocenoz.mlc.api;

import com.gonzalocenoz.mlc.model.productDetail.ProductDetail;
import com.gonzalocenoz.mlc.model.productSearch.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMeliAPI {


    @GET("sites/MLA/search")
    Call<ProductSearch> searchProducts(@Query("q") String query);

    @GET("items/{id}")
    Call<ProductDetail> getProductDetail(@Path("id") String id);

}