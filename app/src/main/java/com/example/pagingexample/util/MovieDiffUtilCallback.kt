package com.example.pagingexample.util

import androidx.recyclerview.widget.DiffUtil
import com.example.pagingexample.model.Movie

class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
  override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem.id == newItem.id
  }

  override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
    return oldItem.title == newItem.title && oldItem.overview == newItem.overview && oldItem.posterPath == newItem.posterPath
  }
}