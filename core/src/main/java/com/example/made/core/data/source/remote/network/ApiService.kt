package com.example.made.core.data.source.remote.network

import com.example.made.core.BuildConfig
import com.example.made.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieList(): MovieListResponse

}