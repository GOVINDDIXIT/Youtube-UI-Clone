package com.govind.youtubebtla.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.govind.youtubebtla.CourseLesson
import com.govind.youtubebtla.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.course_detail_item.view.*


class CourseDetailAdapter(private val courseLessonArray: Array<CourseLesson>) :
    RecyclerView.Adapter<CourseDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val courseDetailItem = layoutInflater.inflate(R.layout.course_detail_item, parent, false)
        return CourseDetailViewHolder(courseDetailItem)
    }

    override fun getItemCount(): Int {
        return courseLessonArray.size
    }

    override fun onBindViewHolder(holder: CourseDetailViewHolder, position: Int) {
        val courseLesson = courseLessonArray[position]
        holder.itemView.textView_course_title.text = courseLesson.name
        holder.itemView.textView_duration.text = courseLesson.duration

        val thumbnailImageView = holder.itemView.imageView_course
        Picasso.with(holder.itemView.context).load(courseLesson.imageUrl).into(thumbnailImageView)

        holder.courseLesson = courseLesson
    }
}

class CourseDetailViewHolder(view: View, var courseLesson: CourseLesson? = null) :
    RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(view.context, Uri.parse(courseLesson?.link))
        }
    }
}