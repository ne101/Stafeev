package com.example.stafeev.data.network.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("kinopoiskId")
    val id: Int,
    @SerializedName("nameRu")
    val name: String,
    @SerializedName("posterUrl")
    val posterUrl: String,
    @SerializedName("ratingKinopoisk")
    val rating: Double,
    @SerializedName("year")
    val year: Int,
    @SerializedName("description")
    val description: String
)