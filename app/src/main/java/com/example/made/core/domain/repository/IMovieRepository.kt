package com.example.made.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.made.core.data.Resource
import com.example.made.core.domain.model.Movie

interface IMovieRepository {

    fun getAllMovie(): LiveData<Resource<List<Movie>>>

    fun getFavoriteMovie(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

//    fun getAllMovie(): Flow<Resource<List<Movie>>>
//
//    fun getAllMovie(query: String): Flow<Resource<List<Movie>>>
//
//    fun getFavorite(): Flow<List<Movie>>
//
//    fun checkFavorite(favoriteId: String): Flow<Int>
//
//    fun insertFavorite(favorite: Movie)
//
//    fun deleteFavorite(favorite: Movie)
////////////////////////////
//    fun getMovieList(): LiveData<Resource<List<Movie>>>
//
////    fun getMovieDetail(movieId: String?): LiveData<Resource<Movie>>
//
//    fun getFavorites(): LiveData<List<Movie>>
//
//    fun checkFavorite(favoriteId: String): LiveData<Int>
//
//    fun insertFavorite(favorite: Movie)
//
//    fun deleteFavorite(favorite: Movie)

}