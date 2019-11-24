package com.gonzalocenoz.mlc.view.productDetail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gonzalocenoz.mlc.api.MeliAPI;
import com.gonzalocenoz.mlc.model.productDetail.ProductDetail;
import com.gonzalocenoz.mlc.model.productDetail.ProductPicture;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;
import com.gonzalocenoz.mlc.service.ProductService;
import com.gonzalocenoz.mlc.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends ViewModel {

    private ProductService productService;
    private ProductSearchItem selectedProductSearchItem;
    private MutableLiveData<Integer> errorCode = new MutableLiveData<>();

    private MutableLiveData<String> title = new MutableLiveData<>();
    private MutableLiveData<String> price = new MutableLiveData<>();
    private MutableLiveData<String> condition = new MutableLiveData<>();
    private MutableLiveData<String> acceptsMP = new MutableLiveData<>();
    private MutableLiveData<String> availableQuatity = new MutableLiveData<>();

    private MutableLiveData<List<ProductPicture> > pictures = new MutableLiveData<>();

    public ProductDetailViewModel(ProductSearchItem selectedProductSearchItem) {

        this.productService = new ProductService();
        this.selectedProductSearchItem = selectedProductSearchItem;

        this.title.setValue(selectedProductSearchItem.getTitle());
        this.price.setValue( Utils.getInstance().FormatPrice(selectedProductSearchItem.getCurrencyId(),selectedProductSearchItem.getPrice()));

        this.getProductDetail();
    }



    public MutableLiveData<Integer> getProductDetailErrorCode() {
        return errorCode;
    }

    public MutableLiveData<String> getTitle() {
        return title;
    }


    public MutableLiveData<String> getPrice() {
        return price;
    }

    public MutableLiveData<String> getAvailableQuantity() {
        return availableQuatity;
    }

    public MutableLiveData<List<ProductPicture>> getPictures() {
        return pictures;
    }

    public MutableLiveData<String> getAcceptsMP() {
        return acceptsMP;
    }

    public MutableLiveData<String> getCondition() {
        return condition;
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

        this.price.setValue(Utils.getInstance().FormatPrice(productDetail.getCurrencyId(),productDetail.getPrice()));
        this.acceptsMP.setValue(Utils.getInstance().FormatAcceptsMP(productDetail.getAcceptsMercadopago()));
        this.availableQuatity.setValue(Utils.getInstance().FormatAvailableQuatity(productDetail.getAvailableQuantity()));
        this.condition.setValue(Utils.getInstance().FormatCondition(productDetail.getCondition()));


        this.pictures.setValue(productDetail.getPictures());

    }
}
