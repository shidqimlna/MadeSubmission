package com.example.made.di

import com.example.made.core.domain.usecase.MovieInteractor
import com.example.made.core.domain.usecase.MovieUseCase
import com.example.made.detail.DetailViewModel
import com.example.made.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}