package com.example.stafeev.data.database

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithCountriesAndGenres(
    @Embedded val movie: MovieTable,
    @Relation(
        parentColumn = "id",
        entityColumn = "genreId"
    )
    val genres: List<GenresTable>,
    @Relation(
        parentColumn = "id",
        entityColumn = "countryId"
    )
    val countries: List<CountriesTable>
)

