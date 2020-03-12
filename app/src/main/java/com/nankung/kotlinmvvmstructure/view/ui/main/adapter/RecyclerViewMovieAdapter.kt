package com.nankung.kotlinmvvmstructure.view.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.nankung.common.module.extension.view.setGlideImage
import com.nankung.kotlinmvvmstructure.R
import com.nankung.network.model.response.result.MoviesResult
import com.nankung.common.module.base.URLService

class RecyclerViewMovieAdapter(
    private var context: Context,
    private var popularList: List<MoviesResult>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val listSize = popularList.size
        if (listSize > 0) {
            return listSize
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val data = popularList[position]
        val title = data.title
        val voteAverage = data.vote_average.toString()
        val overview = data.overview
        val releaseDate = data.release_date
        val posterPath = data.poster_path.toString()
        viewHolder.textTitle.text = title.toString()
        viewHolder.textVoteAverage.text = voteAverage
        viewHolder.textOverview.text = overview.toString()
        viewHolder.textReleaseDate.text = releaseDate.toString()

        viewHolder.posterPath.setGlideImage("${URLService.TMDB_PHOTO_URL}${data.poster_path}")


    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textTitle: AppCompatTextView =
            itemView.findViewById(R.id.text_title) as AppCompatTextView
        var textVoteAverage: AppCompatTextView =
            itemView.findViewById(R.id.text_vote_average) as AppCompatTextView
        var textOverview: AppCompatTextView =
            itemView.findViewById(R.id.text_overview) as AppCompatTextView
        var textReleaseDate: AppCompatTextView =
            itemView.findViewById(R.id.text_release_date) as AppCompatTextView
        var posterPath: AppCompatImageView =
            itemView.findViewById(R.id.poster_path) as AppCompatImageView
    }
}

