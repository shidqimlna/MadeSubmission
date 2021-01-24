package com.example.made.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.made.core.data.source.remote.network.ApiResponse
import com.example.made.core.data.source.remote.network.MovieAPI
import com.example.made.core.data.source.remote.response.MovieResponse
import com.example.made.core.data.source.remote.response.MovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource constructor(private val mainAPI: MovieAPI) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(mainApi: MovieAPI): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(mainApi)
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<MovieResponse>>> {
        val result = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        mainAPI.create()?.getMovieList()
            ?.enqueue(object : Callback<MovieListResponse> {
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    try {
                        val movieResponse: List<MovieResponse> =
                            response.body()?.results ?: emptyList()
                        if (movieResponse.isNullOrEmpty())
                            result.value = ApiResponse.Empty
                        else
                            result.value = ApiResponse.Success(movieResponse)
                    } catch (e: Exception) {
                        result.value = ApiResponse.Error(e.toString())
                        Log.e("RemoteDataSource", e.toString())
                        throw Exception(e.toString())
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    result.value = ApiResponse.Error(t.toString())
                    Log.e("RemoteDataSource", t.toString())
                    throw Exception(t.toString())
                }
            })
        return result
    }

}


