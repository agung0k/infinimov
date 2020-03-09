package com.example.pagingexample.model

import com.google.gson.annotations.SerializedName

data class ResponseApi<T>(
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    @SerializedName("results")
    val results: List<T>? = null
)