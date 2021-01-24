package com.example.made.core.di

import android.content.Context
import com.example.made.core.data.MovieRepository
import com.example.made.core.data.source.local.LocalDataSource
import com.example.made.core.data.source.local.room.MovieDatabase
import com.example.made.core.data.source.remote.network.MovieAPI
import com.example.made.core.data.source.remote.RemoteDataSource
import com.example.made.core.domain.repository.IMovieRepository
import com.example.made.core.domain.usecase.MovieInteractor
import com.example.made.core.domain.usecase.MovieUseCase
import com.example.made.core.util.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IMovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance(MovieAPI())
        val localDataSource = LocalDataSource.getInstance(MovieDatabase.getInstance(context).movieDao())
        val appExecutors = AppExecutors()
        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideMovieUseCase(context: Context): MovieUseCase = MovieInteractor(provideRepository(context))

}