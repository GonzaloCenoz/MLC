package com.gonzalocenoz.mlc.view.productDetail;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.gonzalocenoz.mlc.model.productDetail.ProductPicture;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailImagesAdapter extends PagerAdapter {

    List<ProductPicture> pictures = new ArrayList<ProductPicture>();
    Context context;

    public ProductDetailImagesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pictures == null? 0 : pictures.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);


        Glide.with(context)
                .load(pictures.get(position).getUrl())
                .into(imageView);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    public void refreshProductPictures(List<ProductPicture> pictures) {
        this.pictures.clear();
        this.pictures.addAll(pictures);

        notifyDataSetChanged();
    }
}
