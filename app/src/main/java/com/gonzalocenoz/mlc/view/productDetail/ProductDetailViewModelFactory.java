package com.gonzalocenoz.mlc.view.productDetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;

public class ProductDetailViewModelFactory implements ViewModelProvider.Factory {

    private ProductSearchItem selectedProductSearchItem;

    public ProductDetailViewModelFactory(ProductSearchItem psi) {
        this.selectedProductSearchItem = psi;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProductDetailViewModel(selectedProductSearchItem);
    }
}
