package com.example.made.core.data.source.remote.network

import com.example.made.core.BuildConfig
import com.example.made.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieList(): MovieListResponse

    @GET("search/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun getSearchMovie(
        @Query("query") query: String
    ): MovieListResponse

}