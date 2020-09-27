package com.epam.valkaryne.openmovieapp.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.epam.valkaryne.openmovieapp.data.api.model.MovieDataModel;
import com.epam.valkaryne.openmovieapp.databinding.ListItemMovieBinding;

public class MoviesAdapter extends PagingDataAdapter<MovieDataModel, MoviesAdapter.MovieViewHolder> {

    public MoviesAdapter() {
        super(new MoviesDiffCallback());
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                ListItemMovieBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieDataModel movie = getItem(position);
        if (movie != null) {
            holder.bind(movie);
        }
    }

    public static final class MovieViewHolder extends RecyclerView.ViewHolder {

        private ListItemMovieBinding binding;

        public MovieViewHolder(ListItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieDataModel item) {
            binding.setMovie(item);
            binding.executePendingBindings();
        }
    }
}

