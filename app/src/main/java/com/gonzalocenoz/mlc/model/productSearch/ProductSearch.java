package com.gonzalocenoz.mlc.model.productSearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductSearch {


    @SerializedName("products")
    private List<ProductSearchItem> products = null;

    public ProductSearch(List<ProductSearchItem> products) {
        this.products = products;
    }

    public List<ProductSearchItem> getProducts() {
        return products;
    }
}
