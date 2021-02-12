package com.example.made.search

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
import com.example.made.databinding.FragmentSearchBinding
import com.example.made.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
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
                view.findNavController().navigate(R.id.action_navigation_search_to_detail, bundle)
            }

            observeQuery()
            observeSearch()

            with(binding.fragmentSearchRecyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }

        }
    }

    @ExperimentalCoroutinesApi
    private fun observeQuery() {
        binding.fragmentSearchEtSearch.doOnTextChanged { text, _, _, _ ->
            if (text != null && text.isNotEmpty()) searchViewModel.setSearchQuery(text.toString())
        }
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observeSearch() {
        searchViewModel.search.observe(viewLifecycleOwner, { movie ->
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
            fragmentSearchProgressBar.root.visibility = View.VISIBLE
            fragmentSearchTvEmpty.visibility = View.GONE
            fragmentSearchErrorWarning.root.visibility = View.GONE
        }
    }

    private fun stateSuccess(empty: Boolean) {
        with(binding) {
            fragmentSearchProgressBar.root.visibility = View.GONE
            fragmentSearchErrorWarning.root.visibility = View.GONE
            fragmentSearchTvEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        }
    }

    private fun stateError() {
        with(binding) {
            fragmentSearchProgressBar.root.visibility = View.GONE
            fragmentSearchTvEmpty.visibility = View.GONE
            fragmentSearchErrorWarning.root.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                fragmentSearchErrorWarning.root.visibility = View.GONE
            }, 2000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}