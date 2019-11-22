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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.gonzalocenoz.mlc.R;
import com.gonzalocenoz.mlc.databinding.ProductSearchItemBinding;
import com.gonzalocenoz.mlc.model.productSearch.*;

import java.util.ArrayList;
import java.util.List;

public class ProductsSearchAdapter extends RecyclerView.Adapter<ProductsSearchAdapter.ProductSearchItemViewHolder> {

    List<ProductSearchItem> productSearchItems = new ArrayList<ProductSearchItem>();
    Context context;

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
        return this.productSearchItems != null ? this.productSearchItems.size() : 0;
    }

    public void refreshProductSearchItems(List<ProductSearchItem> productSearchItems) {
        this.productSearchItems.clear();
        this.productSearchItems.addAll(productSearchItems);
        notifyDataSetChanged();

        //TODO : diff ??
        //https://android.jlelse.eu/smart-way-to-update-recyclerview-using-diffutil-345941a160e0
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

            //TODO : create intent
            Toast.makeText(context, p.getTitle().toString(), Toast.LENGTH_SHORT).show();
        }

        public void setProductSearchItem(ProductSearchItem productSearchItem) {
            productSearchItemBinding.setProductSearchItem(productSearchItem);

            RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop();

            Glide.with(context)
                    .load(productSearchItem.getThumbnail())
                    .thumbnail(0.1f)
                    .apply(requestOptions)
                    .into(imageView);

        }
    }
}
