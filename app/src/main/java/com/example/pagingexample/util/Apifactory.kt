package com.example.pagingexample.util

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apifactory {
  private val authInterceptor = Interceptor { chain ->
    val newUrl = chain.request().url.newBuilder().addQueryParameter("api_key",
        Constants.tmdbApiKey).build()

    val newRequest = chain.request().newBuilder().url(newUrl).build()

    chain.proceed(newRequest)
  }

  private val tmdbClient = OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()

  fun retrofit() = Retrofit.Builder()
      .client(tmdbClient)
      .baseUrl(Constants.tmdbBaseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

  val tmdbApi = retrofit().create(TmdbApi::class.java)
}