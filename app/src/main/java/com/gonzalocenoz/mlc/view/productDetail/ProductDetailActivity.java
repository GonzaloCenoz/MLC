package com.gonzalocenoz.mlc.view.productDetail;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.databinding.ActivityProductDetailBinding;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;

public class ProductDetailActivity extends AppCompatActivity {

    public static String PRODUCT_DETAIL_KEY = "PRODUCT_DETAIL_KEY";

    private ProductDetailViewModel productsDetailViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail);

        ProductSearchItem selectedProductSearchItem;

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            selectedProductSearchItem = null;
        } else {
            selectedProductSearchItem = (ProductSearchItem) extras.getSerializable("PRODUCT_DETAIL_KEY");
        }

        this.productsDetailViewModel = ViewModelProviders.of(this,
                new ProductDetailViewModelFactory(selectedProductSearchItem))
                .get(ProductDetailViewModel.class);

        ActivityProductDetailBinding  b = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        b.setProductDetailViewModel(this.productsDetailViewModel);
    }

}
