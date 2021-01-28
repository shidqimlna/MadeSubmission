package com.example.made.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.made.R
import com.example.made.core.data.Resource
import com.example.made.core.ui.MovieAdapter
import com.example.made.databinding.FragmentHomeBinding
import com.example.made.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val bundle = Bundle()
                bundle.putParcelable(DetailActivity.EXTRA_DATA, selectedData)
                view.findNavController().navigate(R.id.action_navigation_home_to_detail, bundle)
            }

            observeMovie()
            observeQuery()
            observeSearch()

            with(binding.fragmentHomeRecyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }

        }
    }

    private fun observeMovie() {
        homeViewModel.movie.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> stateLoading()
                    is Resource.Success -> {
                        stateSuccess(movie.data?.isEmpty() == true)
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> stateError()
                }
            }
        })
    }

    @ExperimentalCoroutinesApi
    private fun observeQuery() {
        binding.fragmentHomeEtSearch.doOnTextChanged { text, _, _, _ ->
            if (text != null) {
                if (text.isNotEmpty())
                    homeViewModel.setSearchQuery(text.toString())
                else
                    Handler(Looper.getMainLooper()).postDelayed({ observeMovie() }, 500)
            }
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observeSearch() {
        homeViewModel.search.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                when (movie) {
                    is Resource.Loading -> stateLoading()
                    is Resource.Success -> {
                        stateSuccess(movie.data?.isEmpty() == true)
                        movieAdapter.setData(movie.data)
                    }
                    is Resource.Error -> stateError()
                }
            }
        })
    }

    private fun stateLoading() {
        with(binding) {
            fragmentHomeProgressBar.root.visibility = View.VISIBLE
            fragmentHomeTvEmpty.visibility = View.GONE
            fragmentHomeErrorWarning.root.visibility = View.GONE
        }
    }

    private fun stateSuccess(empty: Boolean) {
        with(binding) {
            fragmentHomeProgressBar.root.visibility = View.GONE
            fragmentHomeErrorWarning.root.visibility = View.GONE
            fragmentHomeTvEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        }
    }

    private fun stateError() {
        with(binding) {
            fragmentHomeProgressBar.root.visibility = View.GONE
            fragmentHomeTvEmpty.visibility = View.GONE
            fragmentHomeErrorWarning.root.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                fragmentHomeErrorWarning.root.visibility = View.GONE
            }, 1500)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}