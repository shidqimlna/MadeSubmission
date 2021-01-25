package com.example.made.core.domain.usecase

import com.example.made.core.data.Resource
import com.example.made.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovie(): Flow<List<Movie>>

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