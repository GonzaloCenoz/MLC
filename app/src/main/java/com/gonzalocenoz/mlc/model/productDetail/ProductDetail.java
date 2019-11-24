package com.gonzalocenoz.mlc.model.productDetail;

import com.gonzalocenoz.mlc.model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail extends Product {


    @SerializedName("pictures")
    private List<ProductPicture> pictures;

    public List<ProductPicture> getPictures() {
        return pictures;
    }


}
