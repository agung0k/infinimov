package com.example.pagingexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingexample.R
import com.example.pagingexample.databinding.ItemMovieBinding
import com.example.pagingexample.model.Movie
import com.example.pagingexample.util.Constants

class MovieAdapter(val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {

  private var movies: List<Movie> = ArrayList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
    return MovieViewHolder(view)
  }

  override fun getItemCount() = movies.size

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    val movie = movies.get(position)
    holder.binding?.apply {
      tvTitle.text = movie.title
      tvDesc.text = movie.overview
      Glide.with(ivPoster.context).load("${Constants.tmdbImageUrl}${movie.posterPath}").into(
          ivPoster)
    }
  }

  fun addData(movies: List<Movie>) {
    val indexStart = itemCount
    this.movies += movies
    notifyItemRangeInserted(indexStart, movies.size)
  }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val binding = DataBindingUtil.bind<ItemMovieBinding>(view)
}