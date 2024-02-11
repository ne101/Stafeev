package com.example.stafeev.di

import androidx.lifecycle.ViewModel
import com.example.stafeev.presentaion.viewModels.FavouriteMovieListViewModel
import com.example.stafeev.presentaion.viewModels.MovieDetailInfoViewModel
import com.example.stafeev.presentaion.viewModels.MovieListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavouriteMovieListViewModel::class)
    fun bindFavouriteMovieListViewModel(viewModel: FavouriteMovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailInfoViewModel::class)
    fun bindFavouriteMovieDetailInfoViewModel(viewModel: MovieDetailInfoViewModel): ViewModel
}