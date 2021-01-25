package com.example.made.detail

import androidx.lifecycle.ViewModel
import com.example.made.core.domain.model.Movie
import com.example.made.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)

}

