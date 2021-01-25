package com.example.made.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made.core.domain.usecase.MovieUseCase

class FavoriteViewModel(tourismUseCase: MovieUseCase) : ViewModel() {

    val favoriteMovie = tourismUseCase.getFavoriteMovie().asLiveData()

}

