package com.example.made.ui.detail

import androidx.lifecycle.ViewModel
import com.example.made.core.domain.model.Movie
import com.example.made.core.domain.usecase.MovieUseCase

class DetailViewModel(private val tourismUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newStatus:Boolean) =
        tourismUseCase.setFavoriteMovie(movie, newStatus)
}

