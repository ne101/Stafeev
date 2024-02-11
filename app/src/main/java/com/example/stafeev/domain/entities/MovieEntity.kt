package com.example.stafeev.domain.entities
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class MovieEntity(
    val countries: List<CountryEntity>,
    val genres: List<GenreEntity>,
    val id: Int,
    val name: String,
    val posterUrl: String,
    val rating: Double,
    val year: Int,
    val description: String?
) : Parcelable