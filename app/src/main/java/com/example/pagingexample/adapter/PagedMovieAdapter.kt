package com.example.pagingexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.bumptech.glide.Glide
import com.example.pagingexample.R
import com.example.pagingexample.model.Movie
import com.example.pagingexample.util.Constants
import com.example.pagingexample.util.MovieDiffUtilCallback

class PagedMovieAdapter : PagedListAdapter<Movie, MovieViewHolder>(MovieDiffUtilCallback()) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
    return MovieViewHolder(view)
  }

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    val movie = getItem(position)
    movie?.let {
      holder.binding?.apply {
        tvTitle.text = it.title
        tvDesc.text = it.overview
        Glide.with(ivPoster.context).load("${Constants.tmdbImageUrl}${it.posterPath}").into(
          ivPoster
        )
      }
    }
  }
}