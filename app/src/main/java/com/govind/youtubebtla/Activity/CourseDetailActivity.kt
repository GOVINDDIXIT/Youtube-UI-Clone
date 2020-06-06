package com.govind.youtubebtla.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.govind.youtubebtla.Adapter.CourseDetailAdapter
import com.govind.youtubebtla.Adapter.CustomViewHolder
import com.govind.youtubebtla.CourseLesson
import com.govind.youtubebtla.R
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class CourseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val navBarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navBarTitle

        recycler_view_main.layoutManager = LinearLayoutManager(this)

        val id = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY, -1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id=$id"
        fetchJson(courseDetailUrl)
    }

    private fun fetchJson(courseDetailUrl: String) {
        val request = Request.Builder().url(courseDetailUrl).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@CourseDetailActivity, "Failed", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                val gson = GsonBuilder().create()
                val courseLesson = gson.fromJson(body, Array<CourseLesson>::class.java)

                runOnUiThread {
                    recycler_view_main.adapter =
                        CourseDetailAdapter(courseLesson)
                }
            }
        })
    }
}