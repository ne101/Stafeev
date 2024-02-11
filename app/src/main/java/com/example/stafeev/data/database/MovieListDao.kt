package com.example.stafeev.data.database

import androidx.room.*

@Dao
interface MovieListDao {

    @Transaction
    @Query("SELECT * FROM movie_table")
    suspend fun getFavouriteMovieList(): List<MovieWithCountriesAndGenres>

    @Transaction
    @Query("SELECT * FROM movie_table WHERE id=:id")
    suspend fun getFavouriteMovie(id: Int): MovieWithCountriesAndGenres

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieTable: MovieTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genresTable: GenresTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(countriesTable: CountriesTable)

    @Query("DELETE FROM movie_table WHERE id=:id")
    suspend fun deleteItem(id: Int)
}