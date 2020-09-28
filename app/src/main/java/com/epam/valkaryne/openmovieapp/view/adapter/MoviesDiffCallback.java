package com.epam.valkaryne.openmovieapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo;

class MoviesDiffCallback extends DiffUtil.ItemCallback<MovieInfo> {

    @Override
    public boolean areItemsTheSame(@NonNull MovieInfo oldItem, @NonNull MovieInfo newItem) {
        return oldItem.getImdbID().equals(newItem.getImdbID());
    }

    @Override
    public boolean areContentsTheSame(@NonNull MovieInfo oldItem, @NonNull MovieInfo newItem) {
        return oldItem.equals(newItem);
    }
}
