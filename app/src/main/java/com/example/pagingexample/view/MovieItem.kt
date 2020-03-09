package com.example.pagingexample.view

import com.bumptech.glide.Glide
import com.example.pagingexample.R
import com.example.pagingexample.databinding.ItemMovieBinding
import com.example.pagingexample.model.Movie
import com.example.pagingexample.util.Constants
import com.xwray.groupie.databinding.BindableItem

class MovieItem(val movie: Movie): BindableItem<ItemMovieBinding>() {
    override fun getLayout() = R.layout.item_movie

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        viewBinding?.apply {
            tvTitle.text = movie.title
            tvDesc.text = movie.overview
            Glide.with(ivPoster.context).load("${Constants.tmdbImageUrl}${movie.posterPath}").into(
                ivPoster)
        }
    }
}