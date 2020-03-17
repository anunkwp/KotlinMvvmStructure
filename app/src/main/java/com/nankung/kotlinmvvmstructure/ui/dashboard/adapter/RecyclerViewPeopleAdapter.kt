package com.nankung.kotlinmvvmstructure.ui.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.nankung.common.module.extension.view.setGlideImage
import com.nankung.kotlinmvvmstructure.R
import com.nankung.common.module.base.URLService
import com.nankung.network.model.response.result.PeopleResult

class RecyclerViewPeopleAdapter(
    private var context: Context,
    private var peopleList: List<PeopleResult>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.item_people, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val listSize = peopleList.size
        if (listSize > 0) {
            return listSize
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolder
        val data = peopleList[position]
        val id = data.id
        val known_for_department = data.known_for_department.toString()
        val name = data.name
        viewHolder.textId.text = id.toString()
        viewHolder.textType.text = known_for_department
        viewHolder.textName.text = name.toString()
        viewHolder.posterPath.setGlideImage("${URLService.TMDB_PHOTO_URL}${data.profile_path}")


    }

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textId: AppCompatTextView =
            itemView.findViewById(R.id.text_id) as AppCompatTextView
        var textType: AppCompatTextView =
            itemView.findViewById(R.id.text_type) as AppCompatTextView
        var textName: AppCompatTextView =
            itemView.findViewById(R.id.text_name) as AppCompatTextView
        var posterPath: AppCompatImageView =
            itemView.findViewById(R.id.poster_path) as AppCompatImageView
    }
}

