package com.example.pagingexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexample.R
import com.example.pagingexample.databinding.ActivityExampleGroupieBinding
import com.example.pagingexample.model.Movie
import com.example.pagingexample.util.MovieItemDecorator
import com.example.pagingexample.viewModel.MovieViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class ExampleGroupieActivity : AppCompatActivity() {
  private lateinit var viewModel: MovieViewModel
  private lateinit var binding: ActivityExampleGroupieBinding

  private lateinit var movieAdapter: GroupAdapter<GroupieViewHolder>

  //pagination variable
  private var currentPage = 1
  private var maxPage = 100
  private var isReadyLoadMore = true

  companion object {
    private val screenName = "Groupie example"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_example_groupie)

    supportActionBar?.title = screenName
    viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

    initList()
    initObserver()
    initData()
  }

  private fun initList() {
    binding.rvMovie.apply {
      layoutManager = LinearLayoutManager(this@ExampleGroupieActivity)
      addItemDecoration(MovieItemDecorator(resources.getDimensionPixelSize(R.dimen.margin_16)))

      movieAdapter = GroupAdapter()
      adapter = movieAdapter

      addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
          super.onScrolled(recyclerView, dx, dy)
          val isNeedToLoad = movieAdapter.itemCount - (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() < 12
          if (dy > 0 && isNeedToLoad && isReadyLoadMore && currentPage < maxPage) {
            isFetchData(true)
            currentPage++
            viewModel.fetchPopular(currentPage)
            Log.d("scroll", "scrolled bottom. page $currentPage")
          }
        }
      })
    }
  }

  private fun initObserver() {
    viewModel.getMovieData().observe(this, Observer {
      movieAdapter.addAll(it.toBindableItem())
      isFetchData(false)
    })
  }

  private fun initData() {
    viewModel.fetchPopular()
  }

  private fun isFetchData(isFetch: Boolean) {
    isReadyLoadMore = !isFetch
    binding.pbLoad.visibility = if (isFetch) View.VISIBLE else View.GONE
  }

  private fun List<Movie>.toBindableItem() = this.map {
    MovieItem(it)
  }
}
