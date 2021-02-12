package com.example.made.core.data

import com.example.made.core.data.source.local.LocalDataSource
import com.example.made.core.data.source.remote.RemoteDataSource
import com.example.made.core.data.source.remote.network.ApiResponse
import com.example.made.core.domain.model.Movie
import com.example.made.core.domain.repository.IMovieRepository
import com.example.made.core.util.AppExecutors
import com.example.made.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.util.*

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getPopularMovies().first()) {
                is ApiResponse.Success -> {
                    val movieEntity = DataMapper.mapResponsesToEntities(apiResponse.data)
                    val movie = DataMapper.mapEntitiesToDomain(movieEntity)
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Empty -> {
                    val movie = ArrayList<Movie>() as List<Movie>
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getTopRatedMovies().first()) {
                is ApiResponse.Success -> {
                    val movieEntity = DataMapper.mapResponsesToEntities(apiResponse.data)
                    val movie = DataMapper.mapEntitiesToDomain(movieEntity)
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Empty -> {
                    val movie = ArrayList<Movie>() as List<Movie>
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getUpcomingMovies().first()) {
                is ApiResponse.Success -> {
                    val movieEntity = DataMapper.mapResponsesToEntities(apiResponse.data)
                    val movie = DataMapper.mapEntitiesToDomain(movieEntity)
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Empty -> {
                    val movie = ArrayList<Movie>() as List<Movie>
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }

    override fun getSearchMovie(query: String): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getSearchMovie(query).first()) {
                is ApiResponse.Success -> {
                    val movieEntity = DataMapper.mapResponsesToEntities(apiResponse.data)
                    val movie = DataMapper.mapEntitiesToDomain(movieEntity)
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Empty -> {
                    val movie = ArrayList<Movie>() as List<Movie>
                    emit(Resource.Success(movie))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<Movie>>(apiResponse.errorMessage))
                }
            }
        }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun checkFavorite(movieId: String): Flow<Int> =
        localDataSource.checkFavorite(movieId)

    override fun insertFavorite(movie: Movie) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        if (movieEntity != null) appExecutors.diskIO()
            .execute { localDataSource.insertFavorite(movieEntity) }
    }

    override fun deleteFavorite(movie: Movie) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        if (movieEntity != null) appExecutors.diskIO()
            .execute { localDataSource.deleteFavorite(movieEntity) }
    }

}