package com.example.stafeev.data.network.api

import com.example.stafeev.data.network.models.Movie
import com.example.stafeev.data.network.models.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @GET("api/v2.2/films?order=NUM_VOTE&type=FILM")
    @Headers("x-api-key: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun loadMovieList(): MovieListResponse

    @GET("api/v2.2/films/{id}")
    @Headers("x-api-key: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
    suspend fun loadMovie(@Path("id") id: Int): Movie

}