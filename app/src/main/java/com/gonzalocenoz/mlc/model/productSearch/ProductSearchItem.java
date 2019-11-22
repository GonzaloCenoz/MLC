package com.gonzalocenoz.mlc.model.productSearch;

import com.google.gson.annotations.SerializedName;

public class ProductSearchItem {

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


  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getFormatedPrice()
  {return price.toString();
    //AppUtils.getInstance().getCurrencySymbol(currencyId)+ AppUtils.getInstance().formatPrice(price.intValue());
  }

  public String getConditionText()
  {
    return condition;
    // return AppUtils.getInstance().getConditionText(condition);
  }

  public String getThumbnail() {
    return thumbnail;
  }

}