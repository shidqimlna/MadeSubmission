package com.example.made.core.data.source.local

import com.example.made.core.data.source.local.entity.MovieEntity
import com.example.made.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovies(movieList: List<MovieEntity>) = movieDao.insertMovies(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
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