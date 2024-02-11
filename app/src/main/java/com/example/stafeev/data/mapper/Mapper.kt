package com.example.stafeev.data.mapper

import com.example.stafeev.data.database.*
import com.example.stafeev.data.network.models.Country
import com.example.stafeev.data.network.models.Genre
import com.example.stafeev.data.network.models.Movie
import com.example.stafeev.data.network.models.MovieListResponse
import com.example.stafeev.domain.entities.CountryEntity
import com.example.stafeev.domain.entities.GenreEntity
import com.example.stafeev.domain.entities.MovieEntity
import javax.inject.Inject

class Mapper @Inject constructor() {

    fun mapMovieModelToEntity(movie: Movie): MovieEntity = MovieEntity(
        countries = movie.countries.map { mapCountiesModelToEntity(it) },
        genres = movie.genres.map { mapGenresModelToEntity(it) },
        id = movie.id,
        name = movie.name,
        posterUrl = movie.posterUrl,
        rating = movie.rating,
        year = movie.year,
        description = movie.description
    )

    fun mapMovieListModelToEntity(movieListResponse: MovieListResponse): List<MovieEntity> {
        return movieListResponse.movies.map { mapMovieModelToEntity(it) }
    }

    private fun mapCountiesModelToEntity(country: Country): CountryEntity = CountryEntity(
        country = country.country
    )

    private fun mapGenresModelToEntity(genre: Genre): GenreEntity = GenreEntity(
        genre = genre.genre
    )

    fun mapMovieTableToEntity(movieTable: MovieWithCountriesAndGenres): MovieEntity = MovieEntity(
        countries = movieTable.countries.map { mapCountriesTableToEntity(it) },
        genres = movieTable.genres.map { mapGenresTableToEntity(it) },
        id = movieTable.movie.id,
        name = movieTable.movie.name,
        posterUrl = movieTable.movie.posterUrl,
        rating = movieTable.movie.rating,
        year = movieTable.movie.year,
        description = movieTable.movie.description
    )

    fun mapMovieListTableToEntity(list: List<MovieWithCountriesAndGenres>): List<MovieEntity> {
        return list.map { mapMovieTableToEntity(it) }
    }

    fun mapMovieEntityToTable(movieEntity: MovieEntity): MovieTable = MovieTable(
        id = movieEntity.id,
        name = movieEntity.name,
        posterUrl = movieEntity.posterUrl,
        rating = movieEntity.rating,
        year = movieEntity.year,
        description = movieEntity.description
    )


    fun mapGenresEntityToTable(genreEntity: GenreEntity, genreId: Int): GenresTable = GenresTable(
        genreId = genreId,
        genre = genreEntity.genre
    )

    fun mapCountriesEntityToTable(countryEntity: CountryEntity, countryId: Int): CountriesTable =
        CountriesTable(
            countryId = countryId,
            country = countryEntity.country
        )

    private fun mapCountriesTableToEntity(country: CountriesTable): CountryEntity = CountryEntity(
        country = country.country
    )

    private fun mapGenresTableToEntity(genre: GenresTable): GenreEntity = GenreEntity(
        genre = genre.genre
    )
}