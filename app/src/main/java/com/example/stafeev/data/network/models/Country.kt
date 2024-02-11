package com.example.stafeev.data.network.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("country")
    val country: String
)