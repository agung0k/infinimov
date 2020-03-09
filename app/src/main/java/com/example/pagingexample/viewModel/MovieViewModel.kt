package com.example.pagingexample.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.pagingexample.model.Movie
import com.example.pagingexample.model.ResponseApi
import com.example.pagingexample.util.Apifactory
import com.example.pagingexample.util.TmdbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
  private val job = SupervisorJob()
  private val coroutineContext = Dispatchers.IO + job
  private var movieApi: TmdbApi

  private val movieData: MutableLiveData<List<Movie>> by lazy {
    MutableLiveData<List<Movie>>()
  }

  init {
    movieApi = Apifactory.tmdbApi
  }

  fun fetchPopular(page: Int = 1) {
    viewModelScope.launch(coroutineContext) {
      val response = movieApi.getPopularMovie(page)
      if (response.isSuccessful) {
        val data = (response.body() as ResponseApi<Movie>).results
        movieData.postValue(data)
      }
    }
  }

  fun getMovieData() = movieData as LiveData<List<Movie>>
}