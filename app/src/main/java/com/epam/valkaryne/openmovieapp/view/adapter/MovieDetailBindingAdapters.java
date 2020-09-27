package com.epam.valkaryne.openmovieapp.view.adapter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.epam.valkaryne.openmovieapp.R;

public final class MovieDetailBindingAdapters {

    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_launcher_background)
                    .override(450, 630)
                    .into(view);
        }
    }
}