package com.example.catapp.api

import com.example.catapp.data.model.Cat
import retrofit2.http.GET
import retrofit2.http.Header

interface CatsRemoteApi {
    @GET("v1/breeds")
    suspend fun getDataList(@Header("x-api-key") apiKey : String)
        : List<Cat>
}