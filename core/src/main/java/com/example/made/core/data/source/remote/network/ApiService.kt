package com.example.made.core.data.source.remote.network

import com.example.made.core.BuildConfig
import com.example.made.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovieList(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): MovieListResponse

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieListResponse

}