package com.example.made.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("vote_average") val voteAverage: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("popularity")val popularity: String? = null,
    @SerializedName("vote_count") val voteCount: String? = null,
) : Parcelable
