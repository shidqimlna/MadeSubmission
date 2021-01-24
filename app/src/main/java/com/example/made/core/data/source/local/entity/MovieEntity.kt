package com.example.made.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "posterPath") var posterPath: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "releaseDate") var releaseDate: String? = null,
    @ColumnInfo(name = "voteAverage") var voteAverage: String? = null,
    @ColumnInfo(name = "popularity")val popularity: String? = null,
    @ColumnInfo(name = "backdropPath") val backdropPath: String? = null,
    @ColumnInfo(name =" voteCount") val voteCount: String? = null,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
//    @ColumnInfo(name = "genres") val genres: String? = null
) : Parcelable