package com.gonzalocenoz.mlc.view.productsSearch;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gonzalocenoz.mlc.utils.SharedPreferencesManager;

public class ProductsSearchViewModelFactory  implements ViewModelProvider.Factory {

        private SharedPreferencesManager mSpm;

        public ProductsSearchViewModelFactory(SharedPreferencesManager spm) {
            mSpm = spm;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ProductsSearchViewModel(mSpm);
        }

}

