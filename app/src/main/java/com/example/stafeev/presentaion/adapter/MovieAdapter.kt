package com.example.stafeev.presentaion.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.stafeev.R
import com.example.stafeev.databinding.MovieCardBinding
import com.example.stafeev.domain.entities.MovieEntity
import java.util.*

interface OnMovieClick {
    fun onMovieCLickListener(movieEntity: MovieEntity)
    fun addMovieLingClickListener(movieEntity: MovieEntity)
    fun removeMovieLingClickListener(movieEntity: MovieEntity)
    fun checkMovieInDBOrNot(movieEntity: MovieEntity): Boolean
}

class MovieAdapter(private val onMovieClick: OnMovieClick) : ListAdapter<MovieEntity, MovieViewHolder>(MovieDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val binding = holder.binding
        val movie = getItem(position)
        val context = holder.itemView.context
        with(binding) {
            tvName.text = movie.name
            tvYear.text = movie.year.toString()
            tvRating.text = movie.rating.toString()
            val dpValue = 5
            val scale: Float = context.resources.displayMetrics.density
            val pxValue = (dpValue * scale + 0.5f).toInt()

            Glide.with(context)
                .load(movie.posterUrl)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(pxValue)))
                .into(ivPoster)
            if (onMovieClick.checkMovieInDBOrNot(movie)) {
                ivStar.visibility = View.VISIBLE
            }
            root.setOnLongClickListener {
                if (onMovieClick.checkMovieInDBOrNot(movie)) {
                    ivStar.visibility = View.INVISIBLE
                    onMovieClick.removeMovieLingClickListener(movie)
                } else {
                    ivStar.visibility = View.VISIBLE
                    onMovieClick.addMovieLingClickListener(movie)
                }
                true
            }
            root.setOnClickListener {
                onMovieClick.onMovieCLickListener(movie)
            }
        }
    }


}