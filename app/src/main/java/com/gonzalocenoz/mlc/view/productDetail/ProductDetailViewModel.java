package com.gonzalocenoz.mlc.view.productDetail;

import android.graphics.Picture;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gonzalocenoz.mlc.api.MeliAPI;
import com.gonzalocenoz.mlc.model.productDetail.ProductDetail;
import com.gonzalocenoz.mlc.model.productDetail.ProductPicture;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;
import com.gonzalocenoz.mlc.service.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends ViewModel {

    private ProductService productService;
    private ProductSearchItem selectedProductSearchItem;
    private MutableLiveData<Integer> errorCode = new MutableLiveData<>();

    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> currencyId = new MutableLiveData<>();
    private MutableLiveData<String> price = new MutableLiveData<>();
    private MutableLiveData<List<ProductPicture> > pictures = new MutableLiveData<>();

    public ProductDetailViewModel(ProductSearchItem selectedProductSearchItem) {

        this.productService = new ProductService();
        this.selectedProductSearchItem = selectedProductSearchItem;
        this.title.setValue(selectedProductSearchItem.getTitle());
        this.currencyId.setValue(selectedProductSearchItem.getCurrencyId());
        this.price.setValue(selectedProductSearchItem.getPrice());

        this.getProductDetail();
    }



    public MutableLiveData<Integer> getProductDetailErrorCode() {
        return errorCode;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }

    public MutableLiveData<String> getCurrencyId() {
        return currencyId;
    }

    public MutableLiveData<String> getPrice() {
        return price;
    }

    public MutableLiveData<List<ProductPicture>> getPictures() {
        return pictures;
    }


    private void getProductDetail() {

        Callback<ProductDetail> callback = new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {

                if(response.code() == MeliAPI.RESPONSE_CODE_OK) {
                    setProductDetail(response.body());
                }
                else
                {
                    errorCode.setValue( response.code());
                }
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {
                errorCode.setValue(MeliAPI.RESPONSE_CODE_INTERNAL_SERVER_ERROR);
            }
        };

        productService.getProductDetail(this.selectedProductSearchItem.getId(), callback);
    }

    private void setProductDetail(ProductDetail productDetail) {

        this.title.setValue(productDetail.getTitle());
        this.currencyId.setValue(productDetail.getCurrencyId());
        this.price.setValue(productDetail.getPrice());
        this.pictures.setValue(productDetail.getPictures());
    }
}
