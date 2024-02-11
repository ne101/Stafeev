package com.example.stafeev.di

import android.app.Application
import com.example.stafeev.data.database.AppDataBase
import com.example.stafeev.data.database.MovieListDao
import com.example.stafeev.data.network.api.ApiFactory
import com.example.stafeev.data.network.api.ApiService
import com.example.stafeev.data.repositoryImpl.RepositoryImpl
import com.example.stafeev.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService = ApiFactory.apiService

        @ApplicationScope
        @Provides
        fun provideMovieListDao(application: Application): MovieListDao {
            return AppDataBase.getInstance(application).movieListDao()
        }

    }

}