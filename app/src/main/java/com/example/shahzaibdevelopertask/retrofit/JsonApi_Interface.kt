package com.example.shahzaibdevelopertask.retrofit

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JsonApi_Interface {

       @GET("photos")
       suspend fun getPhotos(): Response<List<JsonApiResponse_DataClass>>
}