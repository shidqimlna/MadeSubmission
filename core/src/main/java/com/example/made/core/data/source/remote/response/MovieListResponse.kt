package com.example.made.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListResponse(
    val results: List<MovieResponse>
) : Parcelable