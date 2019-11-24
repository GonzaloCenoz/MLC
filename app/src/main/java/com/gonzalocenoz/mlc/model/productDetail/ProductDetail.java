package com.gonzalocenoz.mlc.model.productDetail;

import android.graphics.Picture;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("currency_id")
    private String currencyId;

    @SerializedName("price")
    private String price;

    @SerializedName("pictures")
    private List<ProductPicture> pictures;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public String getPrice() {
        return price;
    }

    public List<ProductPicture> getPictures() {
        return pictures;
    }
}
