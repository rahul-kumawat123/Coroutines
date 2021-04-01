package com.example.coroutines

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("posts")
    fun getData() : retrofit2.Call<List<DataModel>>
}