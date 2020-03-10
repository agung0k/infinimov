package com.example.pagingexample.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.pagingexample.model.Movie
import com.example.pagingexample.model.ResponseApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MovieDataSource(val coroutine: CoroutineScope) : PageKeyedDataSource<Int, Movie>() {
  private val job = SupervisorJob()
  private val coroutineContext = Dispatchers.IO + job
  private var movieApi: TmdbApi

  private val fetchState: MutableLiveData<Boolean> by lazy {
    MutableLiveData<Boolean>()
  }

  init {
    movieApi = Apifactory.tmdbApi
  }

  override fun loadInitial(
    params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>
  ) {
    fetchState.postValue(true)
    coroutine.launch(coroutineContext) {
      val response = movieApi.getPopularMovie(1)
      if (response.isSuccessful) {
        val data = (response.body() as ResponseApi<Movie>).results
        callback.onResult(data ?: listOf(), null, 1)
      }
      fetchState.postValue(false)
    }
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    fetchState.postValue(true)
    coroutine.launch(coroutineContext) {
      val response = movieApi.getPopularMovie(params.key)
      if (response.isSuccessful) {
        val data = (response.body() as ResponseApi<Movie>).results
        callback.onResult(data ?: listOf(), params.key + 1)
      }
      fetchState.postValue(false)
    }
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>){}

  fun getFetchState() = fetchState as LiveData<Boolean>
}