package com.example.made.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.made.core.di.Injection
import com.example.made.core.domain.usecase.MovieUseCase
import com.example.made.ui.detail.DetailViewModel
import com.example.made.ui.favorite.FavoriteViewModel
import com.example.made.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideMovieUseCase(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        with(modelClass) {
            return when {
                isAssignableFrom(HomeViewModel::class.java) -> {
                    HomeViewModel(movieUseCase) as T
                }
                isAssignableFrom(FavoriteViewModel::class.java) -> {
                    FavoriteViewModel(movieUseCase) as T
                }
                isAssignableFrom(DetailViewModel::class.java) -> {
                    DetailViewModel(movieUseCase) as T
                }
                else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        }
    }
}
