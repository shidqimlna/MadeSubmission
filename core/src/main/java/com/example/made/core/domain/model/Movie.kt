package com.example.made.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var movieId: String? = null,
    var title: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null,
    var voteAverage: String? = null,
    var voteCount: String? = null,
    var popularity: String? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null
) : Parcelable

