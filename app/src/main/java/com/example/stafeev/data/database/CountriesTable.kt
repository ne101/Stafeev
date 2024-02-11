package com.example.stafeev.data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "countries_table", foreignKeys = [ForeignKey(entity = MovieTable::class,
    parentColumns = ["id"],
    childColumns = ["countryId"],
    onDelete = ForeignKey.CASCADE)])
data class CountriesTable(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val countryId: Int,
    val country: String,
)
