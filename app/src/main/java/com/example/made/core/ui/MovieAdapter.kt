package com.example.made.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.made.R
import com.example.made.core.domain.model.Movie
import com.example.made.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso
import java.util.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bindView(data)
    }

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