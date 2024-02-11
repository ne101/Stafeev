package com.example.stafeev.domain.usecases

import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.domain.repository.Repository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(id: Int): MovieEntity {
        return repository.getMovie(id)
    }
}