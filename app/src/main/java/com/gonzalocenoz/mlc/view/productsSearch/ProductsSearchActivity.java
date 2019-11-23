package com.gonzalocenoz.mlc.view.productsSearch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchHistoryItem;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;
import com.gonzalocenoz.mlc.service.ProductService;
import com.gonzalocenoz.mlc.utils.SharedPreferencesManager;

import java.util.List;


public class ProductsSearchActivity extends AppCompatActivity {

    RecyclerView recyclerViewProductsSearch;
    RecyclerView recyclerViewProductSearchHistory;
    ProgressBar progressBarLoadingProductSearch;
    SearchView searchView;
    ProductsSearchAdapter productSearchAdapter;
    ProductsSearchHistoryAdapter productSearchHistoryAdapter;
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
        this.recyclerViewProductSearchHistory = findViewById(R.id.recyclerViewProductsSearchHistory);
        this.progressBarLoadingProductSearch = findViewById(R.id.progressBarLoadingProductSearch);
        this.searchView = findViewById(R.id.searchView);



        this.productsSearchViewModel = ViewModelProviders.of(this,
                new ProductsSearchViewModelFactory(new SharedPreferencesManager(this)))
                .get(ProductsSearchViewModel.class);

        this.productSearchAdapter = new ProductsSearchAdapter(this);
        recyclerViewProductsSearch.setAdapter(this.productSearchAdapter);
        this.recyclerViewProductsSearch.setLayoutManager(new LinearLayoutManager(this));

        this.productSearchHistoryAdapter= new ProductsSearchHistoryAdapter(this, productsSearchViewModel);
        this.recyclerViewProductSearchHistory.setAdapter(this.productSearchHistoryAdapter);
        this.recyclerViewProductSearchHistory.setLayoutManager(new LinearLayoutManager( this));
    }


    private void setUpControlsBehavior() {

        this.productsSearchViewModel.getProductSearchItems().observe(this, new Observer<List<ProductSearchItem>>() {
            @Override
            public void onChanged(List<ProductSearchItem> productSearchItems) {
                productSearchAdapter.refreshProductSearchItems(productSearchItems);
                progressBarLoadingProductSearch.setVisibility(View.GONE);
                recyclerViewProductsSearch.setVisibility(View.VISIBLE);
                recyclerViewProductSearchHistory.setVisibility(View.INVISIBLE);

                searchView.clearFocus();

            }
        });

        this.productsSearchViewModel.getProductSearchHistoryItems().observe(this, new Observer<List<ProductSearchHistoryItem>>() {
            @Override
            public void onChanged(List<ProductSearchHistoryItem> pshi) {
                productSearchHistoryAdapter.refreshProductSearchHistoryItems(pshi);
            }
        });

        this.productsSearchViewModel.getProductSearchQuery().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!searchView.getQuery().equals(s))
                {

                    getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                    );

                    searchView.setQuery(s,false);

                }
            }
        });

        this.productsSearchViewModel.getProductSearchErrorCode().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {

                progressBarLoadingProductSearch.setVisibility(View.GONE);
                recyclerViewProductSearchHistory.setVisibility(View.VISIBLE);
                recyclerViewProductsSearch.setVisibility(View.INVISIBLE);

                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );

                String m;

                switch (s)
                {
                    case ProductService.RESPONSE_CODE_INTERNAL_SERVER_ERROR:
                        m = getString(R.string.InternalServerError);
                        break;
                    case ProductService.RESPONSE_CODE_NO_CONTENT:
                        m = getString(R.string.noContent);
                        break;
                    default:
                        m =  getString(R.string.genericError) + s.toString();
                        break;
                }

                Toast.makeText(getBaseContext(),m , Toast.LENGTH_SHORT).show();
            }
        });

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                productsSearchViewModel.searchProducts(query);

                recyclerViewProductSearchHistory.setVisibility(View.INVISIBLE);
                progressBarLoadingProductSearch.setVisibility(View.VISIBLE);

                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(recyclerViewProductSearchHistory.getVisibility() != View.VISIBLE)
                {
                    recyclerViewProductSearchHistory.setVisibility(View.VISIBLE);
                    recyclerViewProductsSearch.setVisibility(View.INVISIBLE);
                    productsSearchViewModel.refreshProductSearchHistory();
                }

                return false;
            }
        });

        this.searchView.requestFocus();
    }

}
