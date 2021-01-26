package com.example.made.core.data.source.local

import com.example.made.core.data.source.local.entity.MovieEntity
import com.example.made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun checkFavorite(movieId: String): Flow<Int> = movieDao.checkFavorite(movieId)

    fun insertFavorite(movie: MovieEntity) = movieDao.insertFavorite(movie)

    fun deleteFavorite(movie: MovieEntity) = movieDao.deleteFavorite(movie)

}