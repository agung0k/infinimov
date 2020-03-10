package com.example.pagingexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pagingexample.R
import com.example.pagingexample.adapter.PagedMovieAdapter
import com.example.pagingexample.databinding.ActivityExampleGroupieBinding
import com.example.pagingexample.model.Movie
import com.example.pagingexample.util.MovieDataSource
import com.example.pagingexample.util.MovieItemDecorator
import com.example.pagingexample.viewModel.MovieViewModel

class ExamplePagingActivity : AppCompatActivity(){
  private lateinit var viewModel: MovieViewModel
  private lateinit var binding: ActivityExampleGroupieBinding

  private lateinit var movieAdapter: PagedMovieAdapter

  private var isReadyLoadMore = true

  companion object {
    private val screenName = "Paging example"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_example_groupie)

    supportActionBar?.title = screenName
    viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

    initList()
    initializeList()
  }

  private fun initList() {
    binding.rvMovie.apply {
      layoutManager = LinearLayoutManager(this@ExamplePagingActivity)
      addItemDecoration(MovieItemDecorator(resources.getDimensionPixelSize(R.dimen.margin_16)))

      movieAdapter = PagedMovieAdapter()
      adapter = movieAdapter
    }
  }

  fun initializeList(){
    val config = PagedList.Config.Builder()
      .setPageSize(20)
      .setEnablePlaceholders(true)
      .build()

    val liveData = initializedPagedListBuilder(config).build()

    liveData.observe(this, Observer<PagedList<Movie>> { pagedList ->
      movieAdapter.submitList(pagedList)
    })
  }

  private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Movie> {
    val dataSource = viewModel.getDataSource()
    dataSource.getFetchState().observe(this, Observer {
      isFetchData(it)
    })
    val dataSourceFactory = object: DataSource.Factory<Int, Movie>(){
      override fun create(): DataSource<Int, Movie> {
        return dataSource
      }

    }
    return LivePagedListBuilder<Int, Movie>(dataSourceFactory, config)
  }

  private fun isFetchData(isFetch: Boolean) {
    isReadyLoadMore = !isFetch
    binding.pbLoad.visibility = if (isFetch) View.VISIBLE else View.GONE
  }
}
