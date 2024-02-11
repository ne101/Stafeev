package com.example.stafeev.presentaion.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.domain.usecases.GetMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailInfoViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _movie = MutableLiveData<MovieEntity>()
    val movie: MutableLiveData<MovieEntity>
        get() = _movie

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.postValue(getMovieUseCase.invoke(id))
        }
    }
}