package com.gonzalocenoz.mlc.view.productsSearch;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gonzalocenoz.mlc.api.IMeliAPI;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearch;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;
import com.gonzalocenoz.mlc.service.productSearch.ProductSearchService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsSearchViewModel extends ViewModel {

    private MutableLiveData<List<ProductSearchItem>> productSearchItems = new MutableLiveData<>();
    private ProductSearchService productSearchService;
    private int errorCode;

    public ProductsSearchViewModel() {
        this.productSearchService = new ProductSearchService();
    }

    public MutableLiveData<List<ProductSearchItem>> getProductSearchItems() {
        return productSearchItems;
    }


    public void searchProducts(String query) {

        Callback callback = new Callback<ProductSearch>() {
            @Override
            public void onResponse(Call<ProductSearch> call, Response<ProductSearch> response) {

                if(response.code() == IMeliAPI.RESPONSE_OK_CODE) {

                    productSearchItems.setValue(response.body().getProducts());

                    // TODO : empty validation ?
                }
                else
                {
                    errorCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<ProductSearch> call, Throwable t) {

                errorCode = -1;
            }
        };

        productSearchService.searchProducts(query,callback);
    }
}
