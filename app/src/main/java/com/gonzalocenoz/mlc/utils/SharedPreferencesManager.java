package com.gonzalocenoz.mlc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.gonzalocenoz.mlc.model.productSearch.ProductSearchHistoryItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SharedPreferencesManager {

    private static final String PRODUCT_SEARCH_HISTORY_FILE_KEY = "PRODUCT_SEARCH_HISTORY";
    private final Context context;
    private List<ProductSearchHistoryItem> productSearchHistoryItems;


    public SharedPreferencesManager(Context context) {

        this.context = context;
        this.productSearchHistoryItems = this.getProductSearchHistory();

    }

    public void addProductSearchHistory(ProductSearchHistoryItem ps) {
        if (this.productSearchHistoryItems != null && !this.productSearchHistoryItems.contains(ps)) {
            this.productSearchHistoryItems.add(ps);
            this.saveProductSearchHistoryItems();
        }
    }

    public List<ProductSearchHistoryItem> getProductSearchHistory()
    {
        if(this.productSearchHistoryItems == null)
        {

            try
            {
                SharedPreferences sp = getSharedPreferences();

                Gson gson = new Gson();
                String json = sp.getString(PRODUCT_SEARCH_HISTORY_FILE_KEY, "");

                Type type = new TypeToken<List<ProductSearchHistoryItem>>(){}.getType();
                if(!json.isEmpty())
                {
                    this.productSearchHistoryItems = gson.fromJson(json, type);
                }
            }
            catch (Exception ex)
            {
                Log.e("SharedPreferences", ex.getMessage());
            }
            finally {
                if(this.productSearchHistoryItems == null)
                {
                    this.productSearchHistoryItems = new ArrayList<ProductSearchHistoryItem>();
                }
            }

        }


        Collections.sort(productSearchHistoryItems, new Comparator<ProductSearchHistoryItem>() {
            @Override
            public int compare(ProductSearchHistoryItem o1, ProductSearchHistoryItem o2) {
                return o1.getDate().after(o2.getDate())? -1 : 1;
            }
        });


        return this.productSearchHistoryItems;
    }

    private void saveProductSearchHistoryItems()
    {
        try
        {
            SharedPreferences sp = getSharedPreferences();
            SharedPreferences.Editor editor = sp.edit();

            Gson gson = new Gson();
            String json = gson.toJson(this.getProductSearchHistory());

            editor.putString(PRODUCT_SEARCH_HISTORY_FILE_KEY, json);
            editor.commit();
        }
        catch (Exception ex)
        {
            Log.e("SharedPreferences", ex.getMessage());
        }


    }

    private SharedPreferences getSharedPreferences() {
        return this.context.getApplicationContext().getSharedPreferences(PRODUCT_SEARCH_HISTORY_FILE_KEY, Context.MODE_PRIVATE);
    }

}
