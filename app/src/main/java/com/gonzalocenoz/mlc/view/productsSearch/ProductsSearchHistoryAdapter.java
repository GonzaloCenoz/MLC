package com.gonzalocenoz.mlc.view.productsSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.databinding.ProductSearchHistoryItemBinding;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchHistoryItem;

import java.util.ArrayList;
import java.util.List;

public class ProductsSearchHistoryAdapter extends RecyclerView.Adapter<ProductsSearchHistoryAdapter.ProductSearchHistoryItemViewHolder> {

    List<ProductSearchHistoryItem> productSearchHistoryItems = new ArrayList<ProductSearchHistoryItem>();
    Context context;

    public ProductsSearchHistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductSearchHistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProductSearchHistoryItemBinding b = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.product_search_history_item,parent,false);
        ProductSearchHistoryItemViewHolder pshvw = new ProductSearchHistoryItemViewHolder(b);

        return pshvw;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSearchHistoryItemViewHolder holder, int position) {
        holder.setProductSearchHistoryItem(productSearchHistoryItems.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.productSearchHistoryItems != null ? this.productSearchHistoryItems.size() : 0;
    }

    public void refreshProductSearchHistoryItems(List<ProductSearchHistoryItem> productSearchHistoryItems) {
        this.productSearchHistoryItems.clear();
        this.productSearchHistoryItems.addAll(productSearchHistoryItems);
        notifyDataSetChanged();

        //TODO : diff ??
        //https://android.jlelse.eu/smart-way-to-update-recyclerview-using-diffutil-345941a160e0
    }

    public class ProductSearchHistoryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ProductSearchHistoryItemBinding productSearchHistoryItemBinding;

        public ProductSearchHistoryItemViewHolder(ProductSearchHistoryItemBinding productSearchHistoryItemBinding) {
            super(productSearchHistoryItemBinding.getRoot());

            this.productSearchHistoryItemBinding = productSearchHistoryItemBinding;
            this.productSearchHistoryItemBinding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            ProductSearchHistoryItem phi = productSearchHistoryItems.get(getAdapterPosition());

            Toast.makeText(context, phi.getQuery().toString(), Toast.LENGTH_SHORT).show();
        }

        public void setProductSearchHistoryItem(ProductSearchHistoryItem productSearchHistoryItem) {
            productSearchHistoryItemBinding.setProductSearchHistoryItem(productSearchHistoryItem);
        }
    }
}
