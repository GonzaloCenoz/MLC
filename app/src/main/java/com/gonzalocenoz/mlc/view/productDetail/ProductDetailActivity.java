package com.gonzalocenoz.mlc.view.productDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.databinding.ActivityProductDetailBinding;
import com.gonzalocenoz.mlc.model.productDetail.ProductPicture;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;

import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    public static String PRODUCT_DETAIL_KEY = "PRODUCT_DETAIL_KEY";

    private ProductDetailViewModel productsDetailViewModel;
    private ViewPager viewPagerPictures;
    private ProgressBar progressBarLoadingProductDetailImages;
    private ProductDetailImagesAdapter imagesAdapter;
    ActivityProductDetailBinding  binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


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


        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        binding.setLifecycleOwner(this);
        binding.setProductDetailViewModel(this.productsDetailViewModel);



        this.viewPagerPictures = this.findViewById(R.id.viewPagerPictures);
        this.progressBarLoadingProductDetailImages = this.findViewById(R.id.progressBarLoadingProductDetailImages);

        progressBarLoadingProductDetailImages.setVisibility(View.VISIBLE);
        imagesAdapter = new ProductDetailImagesAdapter(this);
        viewPagerPictures.setAdapter(imagesAdapter);


        this.productsDetailViewModel.getProductDetailErrorCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer errorCode) {

                progressBarLoadingProductDetailImages.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(), "Error " , Toast.LENGTH_SHORT).show();

            }
        });

        this.productsDetailViewModel.getPictures().observe(this, new Observer<List<ProductPicture>>() {
            @Override
            public void onChanged(List<ProductPicture> pictures) {

                binding.invalidateAll();
                imagesAdapter.refreshProductPictures(pictures);

                viewPagerPictures.setVisibility(View.VISIBLE);
                progressBarLoadingProductDetailImages.setVisibility(View.GONE);
            }
        });

    }

}
