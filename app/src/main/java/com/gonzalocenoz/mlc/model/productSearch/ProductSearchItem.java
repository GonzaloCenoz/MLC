package com.gonzalocenoz.mlc.model.productSearch;

import com.gonzalocenoz.mlc.utils.Utils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductSearchItem implements Serializable {

  @SerializedName("id")
  private String id;
  @SerializedName("title")
  private String title;
  @SerializedName("price")
  private Double price;
  @SerializedName("currency_id")
  private String currencyId;
  @SerializedName("condition")
  private String condition;
  @SerializedName("thumbnail")
  private String thumbnail;

  public Double getPrice() {
    return price;
  }

  public String getCurrencyId() {
    return currencyId;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getFormatedPrice()
  {
    return  Utils.getInstance().FormatPrice(currencyId, price);
  }

  public String getConditionText()
  {
    return  Utils.getInstance().FormatCondition(condition);
  }

  public String getThumbnail() {
    return thumbnail;
  }

}