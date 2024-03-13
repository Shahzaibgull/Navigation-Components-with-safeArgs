package com.example.shahzaibdevelopertask.retrofit

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JsonApiResponse_DataClass(
    //val albumId: Int,
    //val id: Int,
    val thumbnailUrl: String,
    val title: String
    //val url: String

) : Serializable