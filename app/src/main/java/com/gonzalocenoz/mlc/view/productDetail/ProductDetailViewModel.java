package com.gonzalocenoz.mlc.view.productDetail;

import androidx.lifecycle.ViewModel;

import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;

public class ProductDetailViewModel extends ViewModel {


    private ProductSearchItem selectedProductSearchItem;

    public ProductDetailViewModel(ProductSearchItem selectedProductSearchItem) {

        this.selectedProductSearchItem = selectedProductSearchItem;
    }

    public ProductSearchItem getSelectedProductSearchItem() {
        return selectedProductSearchItem;
    }
}
