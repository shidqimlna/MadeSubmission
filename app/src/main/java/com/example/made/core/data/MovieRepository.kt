package com.example.made.core.data

import com.example.made.core.data.source.local.LocalDataSource
import com.example.made.core.data.source.remote.RemoteDataSource
import com.example.made.core.data.source.remote.network.ApiResponse
import com.example.made.core.data.source.remote.response.MovieResponse
import com.example.made.core.domain.model.Movie
import com.example.made.core.domain.repository.IMovieRepository
import com.example.made.core.util.AppExecutors
import com.example.made.core.util.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()
//                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()


//    override fun getMovieList(): LiveData<Resource<List<Movie>>> =
//        object :
//            NetworkBoundResource<List<Movie>, List<MovieApiItem>>(appExecutors) {
//            public override fun loadFromDB(): LiveData<List<Movie>> {
//                return Transformations.map(localDataSource.getAllMovies()) {
//                    DataMapper.mapEntitiesToDomain(it)
//                }
//            }
//
//            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
//
//            public override fun createCall(): LiveData<ApiResponse<List<MovieApiItem>>> =
//                remoteDataSource.getAllMovies()
//
//            public override fun saveCallResult(data: List<MovieApiItem>) {
//                val movieList = DataMapper.mapResponsesToEntities(data)
//                localDataSource.insertMovies(movieList)
//            }
//        }.asLiveData()


    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }


//    override fun getMovieList(): LiveData<Resource<List<Movie>>> =
//         object :
//            NetworkBoundResource<List<Movie>, List<MovieApiItem>>(appExecutors) {
//            public override fun loadFromDB(): LiveData<List<Movie>> {
//                return Transformations.map(localDataSource.getMovieList()) {
//                    DataMapper.mapEntitiesToDomain(it)
//                }
//            }
//
//            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
//
//            public override fun createCall(): LiveData<ApiResponse<List<MovieApiItem>>> =
//                remoteDataSource.getMovieList()
//
//            public override fun saveCallResult(data: List<MovieApiItem>) {
//                val movieList = DataMapper.mapResponsesToEntities(data)
//                localDataSource.insertMovies(movieList)
//            }
//        }.asLiveData()

//    override fun getMovieDetail(movieId: String?): LiveData<Resource<MovieEntity>> {
//        var codeNull = 0
//        return object : NetworkBoundResource<MovieEntity, MovieApiItem>(appExecutors) {
//            override fun loadFromDB(): LiveData<MovieEntity> =
//                localDataSource.getMovieDetail(movieId)
//
//            override fun shouldFetch(data: MovieEntity?): Boolean {
//                if (data == null)
//                    codeNull = 1
//                else if (data.genres == null || data.runtime == null)
//                    codeNull = 2
//                return data?.genres == null || data.runtime == null
//            }
//
//            override fun createCall(): LiveData<ApiResponse<MovieApiItem>> =
//                remoteDataSource.getMovieDetail(movieId)
//
//            override fun saveCallResult(data: MovieApiItem) {
//                val movie = MovieEntity(
//                    id = data.id,
//                    title = data.title,
//                    overview = data.overview,
//                    releaseDate = data.releaseDate,
//                    runtime = data.runtime,
//                    voteAverage = data.voteAverage,
////                    genres = getGenres(data.genres),
//                    posterPath = data.posterPath
//                )
//
//                if (codeNull == 1)
//                    localDataSource.insertMovie(movie)
//                else if (codeNull == 2)
//                    localDataSource.setDetailMovie(movie)
//            }
//        }.asLiveData()
//    }
//
//    override fun getFavorites(): LiveData<List<Movie>> {
//        return Transformations.map(localDataSource.getFavorites()) {
//            DataMapper.mapEntitiesToDomain(it)
//        }
//    }
//
//    override fun checkFavorite(favoriteId: String): LiveData<Int> =
//        localDataSource.checkFavorite(favoriteId)
//
//    override fun insertFavorite(favorite: Movie) {
//        val movieEntity = DataMapper.mapDomainToEntity(favorite)
//        if (movieEntity != null) {
//            val favoriteEntity = DataMapper.mapMovieEntityToFavoriteEntity(movieEntity)
//            appExecutors.diskIO().execute { localDataSource.insertFavorite(favoriteEntity) }
//        }
//
//        val tourismEntity = DataMapper.mapDomainToEntity(favorite)
//        appExecutors.diskIO().execute { localDataSource.insertFavorite(tourismEntity) }
//    }
//
//    override fun deleteFavorite(favorite: Movie) {
//        val tourismEntity = DataMapper.mapDomainToEntity(favorite)
//        appExecutors.diskIO().execute { localDataSource.deleteFavorite(tourismEntity) }
//    }

}
