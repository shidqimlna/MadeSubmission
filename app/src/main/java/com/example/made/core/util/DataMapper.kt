package com.example.made.core.util

import com.example.made.core.data.source.local.entity.MovieEntity
import com.example.made.core.data.source.remote.response.MovieResponse
import com.example.made.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                overview = it.overview,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.id,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                popularity = it.popularity,
                overview = it.overview,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.movieId,
        title = input.title,
        posterPath = input.posterPath,
        backdropPath = input.backdropPath,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        releaseDate = input.releaseDate,
        popularity = input.popularity,
        overview = input.overview,
        isFavorite = input.isFavorite
    )

}