package com.example.redcarpettask.fragments.main

import News
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.redcarpettask.network.ApiClient
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {
    var apiCallInprogress = MutableLiveData<Boolean>()
    var nointernet = MutableLiveData<Boolean>()
    var data = MutableLiveData<News>()
    var responsestatus = MutableLiveData<String>()
    fun getdata() {
        apiCallInprogress.value = true
        if (ApiClient.getInstance() == null) {
            apiCallInprogress.value = false
            nointernet.value = true
        } else {
            nointernet.value = false
            ApiClient.getAPIService()?.getData()?.enqueue(GET_DATA)
        }
    }

    private val GET_DATA = object : Callback<News> {
        override fun onFailure(call: retrofit2.Call<News>, t: Throwable) {
            apiCallInprogress.value = false
            responsestatus.value = "failed"
            //Failed
        }
        override fun onResponse(call: retrofit2.Call<News>, response: Response<News>) {
            apiCallInprogress.value = false
            if (response.isSuccessful) {
                //Success
                response.body()?.let {
                    responsestatus.value = it.status
                    if (it.status == "error") {
                        responsestatus.value=it.status
                    } else {
                        responsestatus.value="success"
                        data.value = it
                        Log.i("data", "$it")
                    }
                }

            } else {
                //Response is not successful**error**
                responsestatus.value = "error"
            }

        }
    }
}