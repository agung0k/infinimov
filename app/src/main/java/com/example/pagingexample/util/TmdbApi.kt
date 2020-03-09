package com.example.pagingexample.util

import com.example.pagingexample.model.Movie
import com.example.pagingexample.model.ResponseApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/popular")
    suspend fun getPopularMovie(@Query("page") page: Int): Response<ResponseApi<Movie>>
}