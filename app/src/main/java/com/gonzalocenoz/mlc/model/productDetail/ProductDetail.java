package com.gonzalocenoz.mlc.model.productDetail;

import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class ProductDetail {


    @SerializedName("products")
    private Set<ProductSearchItem> products = null;

    public ProductDetail(Set<ProductSearchItem> products) {
        this.products = products;
    }

    public Set<ProductSearchItem> getProducts() {
        return products;
    }
}
