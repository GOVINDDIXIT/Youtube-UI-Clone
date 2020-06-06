package com.govind.youtubebtla.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.govind.youtubebtla.Activity.CourseDetailActivity
import com.govind.youtubebtla.HomeFeed
import com.govind.youtubebtla.R
import com.govind.youtubebtla.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_item.view.*

class MainAdapter(private val homeFeed: HomeFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.main_item, parent, false)
        return CustomViewHolder(item)
    }

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = homeFeed.videos[position]
        holder.itemView.video_title.text = video.name
        holder.itemView.channel_title.text = video.channel.name + " • " + "20K Views\n4 days ago"

        val thumbnailImageView = holder.itemView.video_thumbnail
        Picasso.with(holder.itemView.context).load(video.imageUrl).into(thumbnailImageView)

        val channelImageView = holder.itemView.channel_thumbnail
        Picasso.with(holder.itemView.context).load(video.channel.profileImageUrl)
            .into(channelImageView)
        holder.video = video
    }
}

class CustomViewHolder(view: View, var video: Video? = null) : RecyclerView.ViewHolder(view) {

    companion object {
        const val VIDEO_TITLE_KEY = "VIDEO_TITLE"
        const val VIDEO_ID_KEY = "VIDEO_ID_KEY"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, CourseDetailActivity::class.java)
            intent.putExtra("VIDEO_TITLE", video?.name)
            intent.putExtra("VIDEO_ID_KEY", video?.id)
            view.context.startActivity(intent)
        }
    }
}