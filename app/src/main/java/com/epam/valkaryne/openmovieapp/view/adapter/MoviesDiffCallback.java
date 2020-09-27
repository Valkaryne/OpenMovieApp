package com.epam.valkaryne.openmovieapp.view.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.epam.valkaryne.openmovieapp.data.api.model.MovieDataModel;

class MoviesDiffCallback extends DiffUtil.ItemCallback<MovieDataModel> {

    @Override
    public boolean areItemsTheSame(@NonNull MovieDataModel oldItem, @NonNull MovieDataModel newItem) {
        return oldItem.getImdbID().equals(newItem.getImdbID());
    }

    @Override
    public boolean areContentsTheSame(@NonNull MovieDataModel oldItem, @NonNull MovieDataModel newItem) {
        return oldItem.equals(newItem);
    }
}
