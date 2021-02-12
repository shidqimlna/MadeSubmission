package com.example.made.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made.core.domain.usecase.MovieUseCase

class PopularViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val popular = movieUseCase.getPopularMovies().asLiveData()

}