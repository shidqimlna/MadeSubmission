package com.example.made.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.made.R
import com.example.made.core.domain.model.Movie
import com.example.made.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso
import java.util.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

//    companion object {
//        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
//            override fun areItemsTheSame(
//                oldItem: MovieEntity,
//                newItem: MovieEntity
//            ): Boolean {
//                return oldItem.id == newItem.id
//            }
//
//            override fun areContentsTheSame(
//                oldItem: MovieEntity,
//                newItem: MovieEntity
//            ): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount() = listData.size

//    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder =
//        ListViewHolder(
//            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
//        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bindView(data)
    }

//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val movies = getItem(position)
//        if (movies != null)
//            holder.bindView(movies)
//    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)
        fun bindView(data: Movie) {
            with(binding) {
                Picasso.get().load(IMAGE_URL + data.posterPath).fit().error(R.drawable.ic_errorimage)
                    .into(itemMovieIvPoster)
                itemMovieTvTitle.text = data.title
                itemMovieTvVoteaverage.text = data.voteAverage
                itemMovieTvPopularity.text = data.popularity
                itemMovieTvOverview.text = data.overview
            }
//            movie.let {
//                with(itemView) {
//                    Picasso.get().load(IMAGE_URL + it.posterPath).fit()
//                        .placeholder(R.drawable.loading_decor).error(R.drawable.ic_errorimage)
//                        .into(item_movie_iv_poster)
//                    item_movie_tv_title.text = it.title
//                    item_movie_tv_rating.text = movie.voteAverage
//                    item_movie_tv_overview.text = it.overview
//                    item_movie_cardView.setOnClickListener {
//                        val intent = Intent(context, MovieDetailActivity::class.java)
//                        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie)
//                        context.startActivity(intent)
//                    }
//                }
//            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }

    }

    companion object {
        private const val IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }

}