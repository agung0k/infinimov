package com.example.pagingexample.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexample.databinding.ItemMovieBinding

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val binding = DataBindingUtil.bind<ItemMovieBinding>(view)
}