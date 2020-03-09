package com.example.pagingexample.util

import com.example.pagingexample.BuildConfig

class Constants {
    companion object {
        val tmdbApiKey = BuildConfig.TMDB_API_KEY
        val tmdbBaseUrl = "https://api.themoviedb.org/3/"
        val tmdbImageUrl = "https://image.tmdb.org/t/p/w185/"
    }
}