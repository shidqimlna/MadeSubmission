package com.example.made.core.domain.usecase

import com.example.made.core.domain.model.Movie
import com.example.made.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {

    override fun getPopularMovies() = movieRepository.getPopularMovies()

    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()

    override fun getUpcomingMovies() = movieRepository.getUpcomingMovies()

    override fun getSearchMovie(query: String) = movieRepository.getSearchMovie(query)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun checkFavorite(movieId: String) = movieRepository.checkFavorite(movieId)

    override fun insertFavorite(movie: Movie) = movieRepository.insertFavorite(movie)

    override fun deleteFavorite(movie: Movie) = movieRepository.deleteFavorite(movie)

}