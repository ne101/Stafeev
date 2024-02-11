package com.example.stafeev.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryEntity(
    val country: String
) : Parcelable
