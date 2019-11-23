package com.gonzalocenoz.mlc.model.productSearch;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ProductSearchHistoryItem extends BaseObservable {

    @SerializedName("query")
    private String query;

    @SerializedName("date")
    private Date date;

    public ProductSearchHistoryItem(String query, Date date) {
        this.query = query;
        this.date = date;
    }

    public String getQuery() {
        return query;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return this.query.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        Boolean isProductSearchHistoryItem =  obj  instanceof ProductSearchHistoryItem;
        return isProductSearchHistoryItem && this.getQuery().equals(((ProductSearchHistoryItem)obj).getQuery());
    }

    public void setSelected() {
        notifyPropertyChanged(1);
    }

}
