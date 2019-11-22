package com.gonzalocenoz.mlc.model.productSearch;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductSearch {

    @SerializedName("results")
    private ArrayList<ProductSearchItem> products = null;

    public ProductSearch(ArrayList<ProductSearchItem> products) {
        this.products = products;
    }

    public ArrayList<ProductSearchItem> getProducts() {
        return products;
    }
}
