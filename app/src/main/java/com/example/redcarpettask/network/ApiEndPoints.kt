package com.example.redcarpettask.network

import News
import retrofit2.Call
import retrofit2.http.GET



interface ApiEndPoints {
    @GET("v2/top-headlines?country=us&apiKey=68e051e5f58048999fe5af028cca4cfb")
    fun getData():Call<News>
}