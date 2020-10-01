package com.epam.valkaryne.openmovieapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.epam.valkaryne.openmovieapp.common.loadImageFromUrl
import com.epam.valkaryne.openmovieapp.data.api.model.MovieInfo
import com.epam.valkaryne.openmovieapp.databinding.ListItemMovieBinding

/**
 * Adapter for the list of movies
 */
class MoviesAdapter :
    PagingDataAdapter<MovieInfo, MoviesAdapter.MovieViewHolder>(MoviesDiffCallback()) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let {
            holder.bind(movie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    class MovieViewHolder(private val binding: ListItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieInfo) {
            binding.movieItemTitle.text = item.title
            binding.movieItemYear.text = item.year
            binding.movieItemImage.loadImageFromUrl(item.posterUrl)
        }
    }
}

private class MoviesDiffCallback : DiffUtil.ItemCallback<MovieInfo>() {

    override fun areItemsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem.imdbID == newItem.imdbID
    }

    override fun areContentsTheSame(oldItem: MovieInfo, newItem: MovieInfo): Boolean {
        return oldItem == newItem
    }
}