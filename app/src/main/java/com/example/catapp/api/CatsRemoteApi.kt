package com.example.catapp.api

import com.example.catapp.data.Cat
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CatsRemoteApi {
    @GET("v1/breeds")
    suspend fun getDataList(@Header("x-api-key") apiKey : String)
        : List<Cat> //burası Single<List<Cat>> olmıcak mı, bu şekilde veriler gelir mi
}