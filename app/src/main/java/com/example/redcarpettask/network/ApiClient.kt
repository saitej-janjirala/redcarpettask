package com.example.redcarpettask.network

import android.widget.Toast
import com.example.redcarpettask.utils.Apputil
import com.example.redcarpettask.utils.RedCarpetApplication
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private var mRetrofitInstance: Retrofit? = null
        private var BASE_URL = "http://newsapi.org/"

        fun getInstance(): Retrofit? {
            if(Apputil.isNetworkConnected(RedCarpetApplication.getInstance())) {
                var instance = mRetrofitInstance
                if (instance != null)
                    return instance
                return synchronized(this) {
                    var tempInstance = instance
                    if (tempInstance != null)
                        tempInstance
                    else {
                        tempInstance = Retrofit.Builder().baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        mRetrofitInstance = tempInstance
                        tempInstance
                    }
                }
            } else
                Toast.makeText(RedCarpetApplication.getInstance(), "Please verify your internet connection and retry.", Toast.LENGTH_LONG).show()

            return null
        }
        fun getAPIService(): ApiEndPoints? {
            return getInstance()?.create(ApiEndPoints::class.java)
        }
    }
}