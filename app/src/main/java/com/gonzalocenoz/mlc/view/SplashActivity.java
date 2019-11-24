package com.gonzalocenoz.mlc.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gonzalocenoz.mlc.view.productsSearch.ProductsSearchActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(SplashActivity.this, ProductsSearchActivity.class));
        finish();
    }

}
