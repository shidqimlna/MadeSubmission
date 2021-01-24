package com.example.made.ui.home

import androidx.lifecycle.ViewModel
import com.example.made.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie()


//    private var movieId = MutableLiveData<String>()
//    private var movie: LiveData<Resource<MovieEntity>>? =
//        Transformations.switchMap(movieId) { mMovieId ->
//            mainRepository.getMovieDetail(mMovieId)
//        }
//    private var movies: LiveData<Resource<PagedList<MovieEntity>>>? = null
//
//    fun setMovieId(movieId: String?) {
//        this.movieId.value = movieId
//    }
//
//    fun getMovieDetail(): LiveData<Resource<MovieEntity>> {
//        if (movie == null) movie = mainRepository.getMovieDetail(movieId.value)
//        return movie as LiveData<Resource<MovieEntity>>
//    }
//
//    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> {
//        if (movies == null) movies = mainRepository.getMovieList()
//        return movies as LiveData<Resource<PagedList<MovieEntity>>>
//    }
//
//    fun checkFavoriteMovie(): LiveData<Int>? =
//        movieId.value?.let { mainRepository.checkFavoriteMovie(it) }
//
//    fun insertFavoriteMovie(movieEntity: MovieEntity) {
//        val favorite = convertFavorite(movieEntity)
//        mainRepository.insertFavoriteMovie(favorite)
//    }
//
//    fun deleteFavoriteMovie(movieEntity: MovieEntity) {
//        val favorite = convertFavorite(movieEntity)
//        mainRepository.deleteFavoriteMovie(favorite)
//    }
//
//    private fun convertFavorite(movie: MovieEntity): FavoriteMovieEntity {
//        return FavoriteMovieEntity(
//            id = movie.id,
//            title = movie.title,
//            releaseDate = movie.releaseDate,
//            overview = movie.overview,
//            posterPath = movie.posterPath,
//            runtime = movie.runtime,
//            voteAverage = movie.voteAverage,
//            genres = movie.genres
//        )
//    }
}