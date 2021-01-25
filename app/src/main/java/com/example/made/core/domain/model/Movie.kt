package com.example.made.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var movieId: String,
    var title: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null,
    var voteAverage: String? = null,
    var voteCount: String? = null,
    var popularity: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null,
    var isFavorite: Boolean = false
) : Parcelable

