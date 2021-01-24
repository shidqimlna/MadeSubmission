package com.example.made.core.data.source.remote.network

import com.example.made.BuildConfig
import com.example.made.core.data.source.remote.response.MovieListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun getMovieList(
    ): Call<MovieListResponse>

//    @GET("movie/{movie_id}?api_key=${BuildConfig.API_KEY}")
//    fun getMovieDetail(
//        @Path("movie_id") movie_id: String?
//    ): Call<MovieApiItem>

}