package com.gonzalocenoz.mlc.model.productSearch;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductSearchItem implements Serializable {

  @SerializedName("id")
  private String id;
  @SerializedName("title")
  private String title;
  @SerializedName("price")
  private String price;
  @SerializedName("currency_id")
  private String currencyId;
  @SerializedName("condition")
  private String condition;
  @SerializedName("thumbnail")
  private String thumbnail;

  public String getPrice() {
    return price;
  }

  public String getCurrencyId() {
    return currencyId;
  }

  public String getCondition() {
    return condition;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getFormatedPrice()
  {
    return price.toString();
  }

  public String getConditionText()
  {
    return condition;
  }

  public String getThumbnail() {
    return thumbnail;
  }

}