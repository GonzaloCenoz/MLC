package com.gonzalocenoz.mlc.view.productDetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gonzalocenoz.mlc.api.MeliAPI;
import com.gonzalocenoz.mlc.model.Product;
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
    private MutableLiveData<Integer> errorCode = new MutableLiveData<>();
    private MutableLiveData<Product> product = new MutableLiveData<>();
    private MutableLiveData<List<ProductPicture> > pictures = new MutableLiveData<>();

    public ProductDetailViewModel(ProductSearchItem selectedProductSearchItem) {

        this.productService = new ProductService();
        this.product.setValue(selectedProductSearchItem);

        this.getProductDetail();
    }

    public MutableLiveData<Integer> getProductDetailErrorCode() {
        return errorCode;
    }

    public MutableLiveData<List<ProductPicture>> getPictures() {
        return pictures;
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }

    private void getProductDetail() {

        Callback<ProductDetail> callback = new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {

                if(response.code() == MeliAPI.RESPONSE_CODE_OK) {
                    setProduct(response.body());
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

        productService.getProductDetail(this.product.getValue().getId(), callback);
    }

    private void setProduct(ProductDetail productDetail) {

        this.product.setValue(productDetail);
        this.pictures.setValue(productDetail.getPictures());
    }
}
