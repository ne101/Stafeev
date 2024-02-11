package com.example.stafeev.presentaion.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.domain.usecases.AddFavouriteMovieUseCase
import com.example.stafeev.domain.usecases.GetFavouriteMovieListUseCase
import com.example.stafeev.domain.usecases.GetMovieListUseCase
import com.example.stafeev.domain.usecases.RemoveFavouriteMovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteMovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val addFavouriteMovieUseCase: AddFavouriteMovieUseCase,
    private val removeFavouriteMovieUseCase: RemoveFavouriteMovieUseCase,
    private val getFavouriteMovieListUseCase: GetFavouriteMovieListUseCase
) : ViewModel() {


    private val _favouriteMovieList = MutableLiveData<List<MovieEntity>>()
    val favouriteMovieList: LiveData<List<MovieEntity>>
        get() = _favouriteMovieList

    private val _filterMovieList = MutableLiveData<List<MovieEntity>?>()
    val filterMovieList: MutableLiveData<List<MovieEntity>?>
        get() = _filterMovieList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _favouriteMovieList.postValue(getFavouriteMovieListUseCase.invoke())
        }
    }

    fun addMovieInBD(movieEntity: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavouriteMovieUseCase.invoke(movieEntity)
            _favouriteMovieList.postValue(getFavouriteMovieListUseCase.invoke())
        }
    }

    fun removeMovieFromDB(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavouriteMovieUseCase.invoke(id)
            _favouriteMovieList.postValue(getFavouriteMovieListUseCase.invoke())
        }
    }

    fun filter(name: String) {
        val filteredList = _favouriteMovieList.value?.filter { movie ->
            movie.name.contains(name, ignoreCase = true)
        }
        _filterMovieList.value = filteredList
    }

}