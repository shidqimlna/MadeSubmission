package com.example.made.ui.di

import com.example.made.core.domain.usecase.MovieInteractor
import com.example.made.core.domain.usecase.MovieUseCase
import com.example.made.ui.detail.DetailViewModel
import com.example.made.ui.favorite.FavoriteViewModel
import com.example.made.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}