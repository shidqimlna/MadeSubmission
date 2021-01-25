package com.example.made.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.made.core.data.Resource
import com.example.made.core.ui.MovieAdapter
import com.example.made.databinding.FragmentHomeBinding
import com.example.made.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.fragmentHomeProgressBar.root.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.fragmentHomeProgressBar.root.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.fragmentHomeProgressBar.root.visibility = View.GONE
                            binding.fragmentHomeErrorWarning.root.visibility = View.VISIBLE
                            binding.fragmentHomeErrorWarning.errorWarningTv.text = movie.message ?: ""
                        }
                    }
                }
            })

            with(binding.fragmentHomeRecyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }
        }
    }
}