package com.example.stafeev.domain.usecases

import com.example.stafeev.domain.repository.Repository
import javax.inject.Inject

class RemoveFavouriteMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int) {
        repository.removeFavouriteMovie(id)
    }
}