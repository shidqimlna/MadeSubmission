package com.example.made.upcoming

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.made.R
import com.example.made.core.data.Resource
import com.example.made.core.ui.MovieAdapter
import com.example.made.databinding.FragmentUpcomingBinding
import com.example.made.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class UpcomingFragment : Fragment() {

    private val upcomingViewModel: UpcomingViewModel by viewModel()

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val bundle = Bundle()
                bundle.putParcelable(DetailActivity.EXTRA_DATA, selectedData)
                view.findNavController().navigate(R.id.action_navigation_upcoming_to_detail, bundle)
            }

            observeData()

            with(binding.recyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }

        }
    }

    private fun observeData() {
        upcomingViewModel.upcoming.observe(viewLifecycleOwner, { movie ->
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
            progressBar.root.visibility = View.VISIBLE
            tvEmpty.visibility = View.GONE
            errorWarning.root.visibility = View.GONE
        }
    }

    private fun stateSuccess(empty: Boolean) {
        with(binding) {
            progressBar.root.visibility = View.GONE
            errorWarning.root.visibility = View.GONE
            tvEmpty.visibility = if (empty) View.VISIBLE else View.GONE
        }
    }

    private fun stateError() {
        with(binding) {
            progressBar.root.visibility = View.GONE
            tvEmpty.visibility = View.GONE
            errorWarning.root.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                errorWarning.root.visibility = View.GONE
            }, 2000)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}