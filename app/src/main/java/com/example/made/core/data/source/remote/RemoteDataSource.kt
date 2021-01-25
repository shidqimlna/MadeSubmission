package com.example.made.core.data.source.remote

import android.util.Log
import com.example.made.core.data.source.remote.network.ApiResponse
import com.example.made.core.data.source.remote.network.ApiService
import com.example.made.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource constructor(private val apiService: ApiService) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovieList()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)


//        val result = MutableLiveData<ApiResponse<List<MovieResponse>>>()
//        apiService.create()?.getMovieList()
//            ?.enqueue(object : Callback<MovieListResponse> {
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    try {
//                        val movieResponse: List<MovieResponse> =
//                            response.body()?.results ?: emptyList()
//                        if (movieResponse.isNullOrEmpty())
//                            result.value = ApiResponse.Empty
//                        else
//                            result.value = ApiResponse.Success(movieResponse)
//                    } catch (e: Exception) {
//                        result.value = ApiResponse.Error(e.toString())
//                        Log.e("RemoteDataSource", e.toString())
//                        throw Exception(e.toString())
//                    }
//                }
//
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                    result.value = ApiResponse.Error(t.toString())
//                    Log.e("RemoteDataSource", t.toString())
//                    throw Exception(t.toString())
//                }
//            })
//        return result
    }

}


