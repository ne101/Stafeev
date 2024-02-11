package com.example.stafeev.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.stafeev.domain.entities.CountryEntity
import com.example.stafeev.domain.entities.GenreEntity

@Entity("movie_table")
data class MovieTable(
    @PrimaryKey
    val id: Int,
    val name: String,
    val posterUrl: String,
    val rating: Double,
    val year: Int,
    val description: String?
)
