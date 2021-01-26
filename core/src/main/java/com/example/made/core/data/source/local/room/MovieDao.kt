package com.example.made.core.data.source.local.room

import androidx.room.*
import com.example.made.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movies: MovieEntity)

    @Query("SELECT 1 FROM movie WHERE id = :favoriteId")
    fun checkFavorite(favoriteId: String): Flow<Int>

    @Delete
    fun deleteFavorite(movie: MovieEntity)

}
