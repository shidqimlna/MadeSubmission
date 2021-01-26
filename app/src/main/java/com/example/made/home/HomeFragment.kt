package com.example.made.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                with(binding) {
                    when (movie) {
                        is Resource.Loading -> fragmentHomeProgressBar.root.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            fragmentHomeProgressBar.root.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            fragmentHomeProgressBar.root.visibility = View.GONE
                            fragmentHomeErrorWarning.root.visibility = View.VISIBLE
                            fragmentHomeErrorWarning.errorWarningTv.text = movie.message ?: ""
                        }
                    }
                }
            }
        })
    }

    @ExperimentalCoroutinesApi
    private fun observeQuery() {
        binding.fragmentHomeEtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0 != null) {
                    if (p0.isNotEmpty())
                        homeViewModel.setSearchQuery(p0.toString())
                    else
                        Handler(Looper.getMainLooper()).postDelayed({ observeMovie() }, 500)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observeSearch() {
        homeViewModel.search.observe(viewLifecycleOwner, { movie ->
            if (movie != null) {
                with(binding) {
                    when (movie) {
                        is Resource.Loading -> fragmentHomeProgressBar.root.visibility =
                            View.VISIBLE
                        is Resource.Success -> {
                            fragmentHomeProgressBar.root.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            fragmentHomeProgressBar.root.visibility = View.GONE
                            fragmentHomeErrorWarning.root.visibility = View.VISIBLE
                            fragmentHomeErrorWarning.errorWarningTv.text = movie.message ?: ""
                            Handler(Looper.getMainLooper()).postDelayed({
                                fragmentHomeErrorWarning.root.visibility = View.GONE
                            }, 1500)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}