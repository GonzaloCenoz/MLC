package com.gonzalocenoz.mlc.model.productDetail;

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
    private Double price;

    @SerializedName("accepts_mercadopago")
    private Boolean acceptsMercadopago;

    @SerializedName("available_quantity")
    private Integer availableQuantity;

    @SerializedName("condition")
    private String condition;

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

    public Double getPrice() {
        return price;
    }

    public List<ProductPicture> getPictures() {
        return pictures;
    }

    public Boolean getAcceptsMercadopago() {
        return acceptsMercadopago;
    }


    public Integer getAvailableQuantity() {
        return availableQuantity;
    }


    public String getCondition() {
        return condition;
    }
}
