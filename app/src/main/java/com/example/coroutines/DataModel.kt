package com.example.coroutines

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("id")
    val postId :Int,

    @SerializedName("title")
    val postTitle: String,

    @SerializedName("body")
    val postBody : String
)