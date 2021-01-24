package com.example.made.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.made.core.data.MovieRepository
import com.example.made.core.data.source.local.entity.MovieEntity
import com.example.made.utils.DataDummy
import com.example.made.core.data.Resource
import com.example.made.ui.home.HomeViewModel
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private lateinit var viewModel: HomeViewModel
    private val dummyMovie = DataDummy.generateDummyMovieDetail()
    private val movieId = dummyMovie.id

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mainRepository: MovieRepository

    @Mock
    private lateinit var movieDetailObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var movieListObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedListMovie: PagedList<MovieEntity>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(mainRepository)
        viewModel.setMovieId(movieId)
    }

    @Test
    fun getMovieList() {
        val dummyMovies = Resource.success(pagedListMovie)
        `when`(dummyMovies.data?.size).thenReturn(10)
        `when`(dummyMovies.data?.get(0)).thenReturn(dummyMovie)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovies

        `when`(mainRepository.getMovieList()).thenReturn(movies)
        val movieEntities = viewModel.getMovieList().value?.data
        verify(mainRepository).getMovieList()
        Assert.assertNotNull(movieEntities)
        assertEquals(dummyMovie, movieEntities?.get(0))
        Assert.assertEquals(10, movieEntities?.size)

        viewModel.getMovieList().observeForever(movieListObserver)
        verify(movieListObserver).onChanged(dummyMovies)
    }

    @Test
    fun getMovieDetail() {
        val dummyMovie = Resource.success(dummyMovie)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(mainRepository.getMovieDetail(movieId)).thenReturn(movie)

        viewModel.getMovieDetail().observeForever(movieDetailObserver)

        verify(movieDetailObserver).onChanged(dummyMovie)
    }
}