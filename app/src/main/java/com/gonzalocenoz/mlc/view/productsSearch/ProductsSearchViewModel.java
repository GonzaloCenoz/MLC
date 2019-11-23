package com.gonzalocenoz.mlc.view.productsSearch;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gonzalocenoz.mlc.model.productSearch.*;
import com.gonzalocenoz.mlc.service.ProductService;
import com.gonzalocenoz.mlc.utils.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsSearchViewModel extends ViewModel {

    private MutableLiveData<List<ProductSearchItem>> productSearchItems = new MutableLiveData<>();
    private MutableLiveData<List<ProductSearchHistoryItem>> productSearchHistoryItems= new MutableLiveData<>();
    private MutableLiveData<String> productSearchQuery = new MutableLiveData<>();
    private MutableLiveData<Integer> errorCode = new MutableLiveData<>();

    private ProductService productService;
    private SharedPreferencesManager sharedPreferencesManager;

    public ProductsSearchViewModel(SharedPreferencesManager spm) {
        this.sharedPreferencesManager = spm;
        this.productService = new ProductService();
    }

    public MutableLiveData<List<ProductSearchItem>> getProductSearchItems() {
        return productSearchItems;
    }


    public MutableLiveData<List<ProductSearchHistoryItem>> getProductSearchHistoryItems() {

        return  productSearchHistoryItems;
    }

    public MutableLiveData<String> getProductSearchQuery() {

        return this.productSearchQuery;
    }

    public MutableLiveData<Integer> getProductSearchErrorCode() {

        return this.errorCode;
    }

    public void searchProducts(final String query) {

        this.productSearchQuery.setValue(query);

        Callback<ProductSearch> callback = new Callback<ProductSearch>() {
            @Override
            public void onResponse(Call<ProductSearch> call, Response<ProductSearch> response) {

                if(response.code() == ProductService.RESPONSE_CODE_OK) {

                    ArrayList<ProductSearchItem> f = response.body().getProducts();
                    productSearchItems.setValue(response.body().getProducts());

                    if(f == null || f.isEmpty())
                    {
                        errorCode.setValue(ProductService.RESPONSE_CODE_NO_CONTENT);
                    }
                    else
                    {
                        saveProductSearchHistory(query);
                    }
                }
                else
                {
                    errorCode.setValue( response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductSearch> call, Throwable t) {
                errorCode.setValue(ProductService.RESPONSE_CODE_INTERNAL_SERVER_ERROR);
            }
        };

        productService.searchProducts(query,callback);

    }

    private void saveProductSearchHistory(String query) {
        Date currentTime = Calendar.getInstance().getTime();
        ProductSearchHistoryItem i = new ProductSearchHistoryItem(query, currentTime);

        sharedPreferencesManager.addProductSearchHistory(i);
    }

    public void refreshProductSearchHistory() {
        this.productSearchHistoryItems.setValue(this.sharedPreferencesManager.getProductSearchHistory());
    }
}
