package com.example.stafeev.domain.repository

import androidx.lifecycle.LiveData
import com.example.stafeev.data.network.models.Movie
import com.example.stafeev.domain.entities.MovieEntity

interface Repository {

    fun getMovieList(): LiveData<List<MovieEntity>>
    suspend fun getMovie(id: Int): MovieEntity
    suspend fun getFavouriteMovieList(): List<MovieEntity>
    suspend  fun addFavouriteMovie(movieEntity: MovieEntity)
    suspend fun removeFavouriteMovie(id: Int)

}