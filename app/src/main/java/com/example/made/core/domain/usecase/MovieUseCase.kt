package com.example.made.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.made.core.data.Resource
import com.example.made.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>

    fun getFavoriteMovie(): LiveData<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

//
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
}