package com.gonzalocenoz.mlc.model.productDetail;

import com.google.gson.annotations.SerializedName;

public class ProductPicture {

    @SerializedName("id")
    private String id;

    @SerializedName("url")
    private String url;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
