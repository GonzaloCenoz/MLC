package com.gonzalocenoz.mlc.view.productsSearch;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;

import java.util.List;


public class ProductsSearchActivity extends AppCompatActivity {

    RecyclerView recyclerViewProductsSearch;
    ProgressBar progressBarLoadingProductSearch;
    SearchView searchView;
    ProductsSearchAdapter productSearchAdapter;
    ProductsSearchViewModel productsSearchViewModel;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        inflateControls();

        setUpControlsBehavior();

    }

    private void inflateControls() {
        this.recyclerViewProductsSearch = findViewById(R.id.recyclerViewProductsSearch);
        this.progressBarLoadingProductSearch = findViewById(R.id.progressBarLoadingProductSearch);
        this.searchView = findViewById(R.id.searchView);
        this.productSearchAdapter = new ProductsSearchAdapter(this);

        recyclerViewProductsSearch.setAdapter(this.productSearchAdapter);
        this.recyclerViewProductsSearch.setLayoutManager(new LinearLayoutManager(this));

        //tODO : change layou manager

        this.productsSearchViewModel = ViewModelProviders.of(this).get(ProductsSearchViewModel.class);

        this.productsSearchViewModel.getProductSearchItems().observe(this, new Observer<List<ProductSearchItem>>() {
            @Override
            public void onChanged(List<ProductSearchItem> productSearchItems) {
                productSearchAdapter.refreshProductSearchItems(productSearchItems);
                progressBarLoadingProductSearch.setVisibility(View.INVISIBLE);
            }
        });
    }


    private void setUpControlsBehavior() {

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                productsSearchViewModel.searchProducts(query);
                    return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

}
