package com.example.made.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.made.core.data.source.local.entity.MovieEntity
import com.example.made.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val mainDao: MovieDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(mainDao: MovieDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(mainDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mainDao.getAllMovies()

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = mainDao.getFavoriteMovie()

    fun insertMovies(movieList: List<MovieEntity>) = mainDao.insertMovies(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        mainDao.updateFavoriteMovie(movie)
    }

//    fun getMovieList(): LiveData<List<MovieEntity>> = mainDao.getMovieList()
//
//    fun insertMovies(movie: List<MovieEntity>) {
//        mainDao.insertMovies(movie)
//    }

//    fun getMovieDetail(movieId: String?): LiveData<MovieEntity> = mainDao.getMovieDetail(movieId)

//    fun insertMovie(movies: MovieEntity) = mainDao.insertMovie(movies)

//    fun setDetailMovie(movie: MovieEntity) = mainDao.updateMovie(movie)

//    fun getFavorites(): LiveData<List<FavoriteMovieEntity>> = mainDao.getFavorites()
//
//    fun checkFavorite(favoriteId: String): LiveData<Int> = mainDao.checkFavorite(favoriteId)
//
//    fun insertFavorite(favoriteEntity: FavoriteMovieEntity) = mainDao.insertFavorite(favoriteEntity)
//
//    fun deleteFavorite(favoriteEntity: FavoriteMovieEntity) = mainDao.deleteFavorite(favoriteEntity)

}