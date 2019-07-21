package com.marelyspoon.android.views;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created on 2019-07-21, 23:40.
 * @author Akram Shokri
 */

public class ImageUtility {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext()).load(imageUrl).transition(withCrossFade()).into(imageView);
    }
}