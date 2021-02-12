package com.example.made.di

import com.example.made.core.domain.usecase.MovieInteractor
import com.example.made.core.domain.usecase.MovieUseCase
import com.example.made.detail.DetailViewModel
import com.example.made.popular.PopularViewModel
import com.example.made.search.SearchViewModel
import com.example.made.toprated.TopRatedViewModel
import com.example.made.upcoming.UpcomingViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { PopularViewModel(get()) }
    viewModel { TopRatedViewModel(get()) }
    viewModel { UpcomingViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}