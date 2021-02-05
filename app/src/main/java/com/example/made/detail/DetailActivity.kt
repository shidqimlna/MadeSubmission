package com.example.made.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.made.R
import com.example.made.core.domain.model.Movie
import com.example.made.databinding.ActivityDetailBinding
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

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA) as Movie
        detailViewModel.movie = detailMovie

        var statusFavorite = false
        detailViewModel.checkFavorite()?.observe(this, { state ->
            statusFavorite = if (state != null) state == 1 else false
            setStatusFavorite(statusFavorite)
        })

        binding.activityDetailFabFavorite.setOnClickListener {
            if (statusFavorite) detailViewModel.deleteFavorite()
            else detailViewModel.insertFavorite()
        }

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
                Glide.with(applicationContext).load(IMAGE_URL + it.backdropPath)
                    .error(R.drawable.ic_errorimage).listener(object :
                        RequestListener<Drawable?> {
                        override fun onLoadFailed(
                            @Nullable e: GlideException?,
                            model: Any,
                            target: Target<Drawable?>,
                            isFirstResource: Boolean
                        ): Boolean {
                            contentDetailProgressbar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any,
                            target: Target<Drawable?>,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            contentDetailProgressbar.visibility = View.GONE
                            return false
                        }
                    }).into(contentDetailIvPoster)
//                Picasso.get().load(IMAGE_URL + it.backdropPath).fit()
//                    .error(R.drawable.ic_errorimage)
//                    .into(contentDetailIvPoster, object : Callback {
//                        override fun onSuccess() {
//                            contentDetailProgressbar.visibility = View.GONE
//                        }
//                        override fun onError(e: Exception?) {
//                            contentDetailProgressbar.visibility = View.GONE
//                        }
//                    })
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
