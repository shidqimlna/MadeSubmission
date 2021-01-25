package com.example.made.core.data.source.local.room

import androidx.room.*
import com.example.made.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateFavoriteMovie(tourism: MovieEntity)

//    @Query("SELECT * FROM movieEntity WHERE id = :movieId")
//    fun getMovieDetail(movieId: String?): LiveData<MovieEntity>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertMovie(movies: MovieEntity)

//    @Update
//    fun updateMovie(movie: MovieEntity)

//    @Query("SELECT * FROM favoriteMovieEntity")
//    fun getFavorites(): LiveData<List<FavoriteMovieEntity>>
//
//    @Query("SELECT 1 FROM favoriteMovieEntity WHERE id = :favoriteId")
//    fun checkFavorite(favoriteId: String): LiveData<Int>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertFavorite(favoriteEntity: FavoriteMovieEntity)
//
//    @Delete
//    fun deleteFavorite(favoriteEntity: FavoriteMovieEntity)

}
