package com.gonzalocenoz.mlc.model;

import com.gonzalocenoz.mlc.utils.Utils;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

  @SerializedName("id")
  protected String id;
  @SerializedName("title")
  protected String title;
  @SerializedName("price")
  protected Double price;
  @SerializedName("currency_id")
  protected String currencyId;
  @SerializedName("condition")
  protected String condition;
  @SerializedName("thumbnail")
  protected String thumbnail;
  @SerializedName("accepts_mercadopago")
  protected Boolean acceptsMercadopago;
  @SerializedName("available_quantity")
  protected Integer availableQuantity;

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public String getFormattedPrice()
  {
    return  Utils.getInstance().FormatPrice(currencyId, price);
  }

  public String getFormattedAcceptsMercadopago()
  {
    return  Utils.getInstance().FormatAcceptsMP(acceptsMercadopago);
  }

  public String getFormattedCondition()
  {
    return  Utils.getInstance().FormatCondition(condition);
  }

  public String getFormattedAvailableQuantity()
  {
    return  Utils.getInstance().FormatAvailableQuantity(availableQuantity);
  }
}