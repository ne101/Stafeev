package com.example.stafeev.di

import android.app.Application
import com.example.stafeev.presentaion.fragments.FavouriteMovieListFragment
import com.example.stafeev.presentaion.fragments.MovieDetailInfoFragment
import com.example.stafeev.presentaion.fragments.MovieListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(fragment: MovieListFragment)
    fun inject(fragment: FavouriteMovieListFragment)
    fun inject(fragment: MovieDetailInfoFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}