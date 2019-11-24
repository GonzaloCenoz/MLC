package com.gonzalocenoz.mlc.model.productDetail;

import com.google.gson.annotations.SerializedName;

public class ProductPicture {

//    pictures": [
//    {
//        "id": "883333-MLA31024989587_062019",
//            "url": "http://mla-s2-p.mlstatic.com/883333-MLA31024989587_062019-O.jpg",
//            "secure_url": "https://mla-s2-p.mlstatic.com/883333-MLA31024989587_062019-O.jpg",
//            "size": "500x349",
//            "max_size": "1284x898",
//            "quality": ""
//    },

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
