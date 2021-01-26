package com.example.made.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made.core.domain.model.Movie
import com.example.made.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    var movie: Movie = Movie()

    fun checkFavorite() = movie.movieId?.let { movieUseCase.checkFavorite(it).asLiveData() }

    fun insertFavorite() = movieUseCase.insertFavorite(movie)

    fun deleteFavorite() = movieUseCase.deleteFavorite(movie)

}

