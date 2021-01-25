package com.example.made.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.made.R
import com.example.made.core.domain.model.Movie
import com.example.made.databinding.ActivityDetailBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }

    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            with(binding.activityDetailContent) {
                contentDetailTvTitle.text = it.title
                contentDetailTvVoteaverage.text = it.voteAverage
                contentDetailTvVotecount.text = getString(R.string.vote_count, it.voteCount)
                contentDetailTvPopularity.text = it.popularity
                contentDetailTvReleasedate.text = it.releaseDate
                contentDetailTvOverview.text = it.overview
                Picasso.get().load(IMAGE_URL + it.backdropPath).fit()
                    .error(R.drawable.ic_errorimage)
                    .into(contentDetailIvPoster, object : Callback {
                        override fun onSuccess() {
                            contentDetailProgressbar.visibility = View.GONE
                        }

                        override fun onError(e: Exception?) {
                            contentDetailProgressbar.visibility = View.GONE
                        }
                    })
            }

            var statusFavorite = detailMovie.isFavorite
            setStatusFavorite(statusFavorite)
            binding.activityDetailFabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.activityDetailFabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_fill
                )
            )
        } else {
            binding.activityDetailFabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_empty
                )
            )
        }
    }

}
