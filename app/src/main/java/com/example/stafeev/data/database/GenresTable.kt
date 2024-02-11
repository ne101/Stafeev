package com.example.stafeev.data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "genres_table", foreignKeys = [ForeignKey(entity = MovieTable::class,
    parentColumns = ["id"],
    childColumns = ["genreId"],
    onDelete = ForeignKey.CASCADE)])
data class GenresTable(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val genreId: Int,
    val genre: String,
)
