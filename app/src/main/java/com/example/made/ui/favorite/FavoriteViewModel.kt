package com.example.made.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.made.core.domain.usecase.MovieUseCase

class FavoriteViewModel(tourismUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = tourismUseCase.getFavoriteMovie()
}

