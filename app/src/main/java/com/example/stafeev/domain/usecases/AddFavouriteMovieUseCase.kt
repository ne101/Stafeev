package com.example.stafeev.domain.usecases

import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.domain.repository.Repository
import javax.inject.Inject

class AddFavouriteMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(movieEntity: MovieEntity) {
        repository.addFavouriteMovie(movieEntity)
    }
}