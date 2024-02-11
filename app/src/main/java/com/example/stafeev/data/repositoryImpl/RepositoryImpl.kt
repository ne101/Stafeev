package com.example.stafeev.data.repositoryImpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.stafeev.data.database.MovieListDao
import com.example.stafeev.data.mapper.Mapper
import com.example.stafeev.data.network.api.ApiService
import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: Mapper,
    private val movieListDao: MovieListDao
) : Repository {
    override fun getMovieList(): LiveData<List<MovieEntity>> {
        return liveData{
            val response = apiService.loadMovieList()
            emit(mapper.mapMovieListModelToEntity(response))
        }
    }

    override suspend fun getFavouriteMovieList(): List<MovieEntity> {
       return mapper.mapMovieListTableToEntity(movieListDao.getFavouriteMovieList())
    }

    override suspend fun addFavouriteMovie(movieEntity: MovieEntity) {
        movieListDao.insertMovie(mapper.mapMovieEntityToTable(movieEntity))

        for (genreEntity in movieEntity.genres) {
            movieListDao.insertGenre(mapper.mapGenresEntityToTable(genreEntity, movieEntity.id))
        }

        for (countryEntity in movieEntity.countries) {
            movieListDao.insertCountry(mapper.mapCountriesEntityToTable(countryEntity, movieEntity.id))
        }
    }

    override suspend fun removeFavouriteMovie(id: Int) {
        movieListDao.deleteItem(id)
    }

    override suspend fun getMovie(id: Int): MovieEntity {
        val response = apiService.loadMovie(id)
        return mapper.mapMovieModelToEntity(response)
    }
}