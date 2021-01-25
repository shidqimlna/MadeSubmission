package com.example.made.core.data.source.remote.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiConfig {
    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}

//class MovieAPI {
//    private var retrofit: Retrofit? = null
//
//    fun create(): ApiService? {
//        if (retrofit == null) {
//            val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
//
//            val client = OkHttpClient.Builder()
//                .addInterceptor(logger)
//                .build()
//
//            retrofit = Retrofit.Builder()
//                .baseUrl("https://api.themoviedb.org/3/")
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//        return retrofit?.create(ApiService::class.java)
//    }
//}