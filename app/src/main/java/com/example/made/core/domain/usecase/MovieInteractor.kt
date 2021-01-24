package com.example.made.core.domain.usecase

import com.example.made.core.domain.model.Movie
import com.example.made.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)


//    override fun getAllMovie() = movieRepository.getAllMovie()
//
//    override fun getAllMovie(query: String) = movieRepository.getAllMovie(query)
//
//    override fun getFavorite() = movieRepository.getFavorite()
//
//    override fun checkFavorite(favoriteId: String) = movieRepository.checkFavorite(favoriteId)
//
//    override fun insertFavorite(favorite: Movie) = movieRepository.insertFavorite(favorite)
//
//    override fun deleteFavorite(favorite: Movie) = movieRepository.deleteFavorite(favorite)
}