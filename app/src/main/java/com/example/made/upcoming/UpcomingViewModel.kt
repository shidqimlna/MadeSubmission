package com.example.made.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.made.core.domain.usecase.MovieUseCase

class UpcomingViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val upcoming = movieUseCase.getUpcomingMovies().asLiveData()

}