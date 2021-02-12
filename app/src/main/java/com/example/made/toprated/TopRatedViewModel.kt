package com.example.made.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made.core.domain.usecase.MovieUseCase

class TopRatedViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val topRated = movieUseCase.getTopRatedMovies().asLiveData()

}