package com.example.stafeev.domain.usecases

import androidx.lifecycle.LiveData
import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.domain.repository.Repository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(private val repository: Repository) {
    operator fun invoke(): LiveData<List<MovieEntity>> {
        return repository.getMovieList()
    }
}