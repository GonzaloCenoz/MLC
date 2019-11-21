package com.gonzalocenoz.mlc.view.productsSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.databinding.ProductSearchItemBinding;
import com.gonzalocenoz.mlc.model.productSearch.ProductSearchItem;

import java.util.List;

public class ProductsSearchAdapter extends RecyclerView.Adapter<ProductsSearchAdapter.ProductSearchItemViewHolder> {

    List<ProductSearchItem> productSearchItems;
    Context context;
    LayoutInflater layoutInflater;

    public ProductsSearchAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductSearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ProductSearchItemBinding b = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.product_search_item,parent,false);
        ProductSearchItemViewHolder psvw = new ProductSearchItemViewHolder(b);

        return psvw;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSearchItemViewHolder holder, int position) {
        holder.setProductSearchItem(productSearchItems.get(position));
    }

    @Override
    public int getItemCount()
    {
        return this.productSearchItems == null? 0: this.productSearchItems.size();
    }

    public void refreshProductSearchItems(List<ProductSearchItem> productSearchItems) {
        this.productSearchItems.clear();
        this.productSearchItems.addAll(productSearchItems);
    }

    public class ProductSearchItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ProductSearchItemBinding productSearchItemBinding;
        ImageView imageView;

        public ProductSearchItemViewHolder(ProductSearchItemBinding productSearchItemBinding) {
            super(productSearchItemBinding.getRoot());

            this.productSearchItemBinding = productSearchItemBinding;
            this.productSearchItemBinding.getRoot().setOnClickListener(this);
            imageView = this.productSearchItemBinding.getRoot().findViewById(R.id.imgThumb);
        }


        @Override
        public void onClick(View v) {

            ProductSearchItem p = productSearchItems.get(getAdapterPosition());

            Toast.makeText(context, p.getTitle().toString(), Toast.LENGTH_SHORT).show();
        }

        public void setProductSearchItem(ProductSearchItem productSearchItem) {
            productSearchItemBinding.setProductSearchItem(productSearchItem);
            Glide.with(imageView).load(productSearchItem.getThumbnail()).into(imageView);

        }
    }
}
