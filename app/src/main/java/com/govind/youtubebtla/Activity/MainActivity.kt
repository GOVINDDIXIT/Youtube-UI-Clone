package com.govind.youtubebtla.Activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.govind.youtubebtla.Adapter.MainAdapter
import com.govind.youtubebtla.HomeFeed
import com.govind.youtubebtla.R
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_view_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    private fun fetchJson() {
        val url = "https://api.letsbuildthatapp.com/youtube/home_feed"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(this@MainActivity, "Failed", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread {
                    recycler_view_main.adapter =
                        MainAdapter(homeFeed)
                }
            }
        })
    }
}

