package com.example.stafeev.data.network.models

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("items")
    val movies: List<Movie>
)