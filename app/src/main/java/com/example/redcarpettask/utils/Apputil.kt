package com.example.redcarpettask.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

object Apputil {

    @JvmStatic
    fun isNetworkConnected(ctx: Context): Boolean {
        val connectivityManager: ConnectivityManager
        var connected = false
        try {
            connectivityManager = ctx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            connected = networkInfo != null && networkInfo.isConnected
            return connected
        } catch (e: Exception) {
            Log.v("connectivity", e.toString())
        }
        return connected
    }
}