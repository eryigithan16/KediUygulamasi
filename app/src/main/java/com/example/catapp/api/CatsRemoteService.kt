package com.example.catapp.api

import com.example.catapp.common.Constants
import com.example.catapp.data.Cat
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CatsRemoteService {

    val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CatsRemoteApi::class.java)

     /*suspend fun getCatListDataFromApi() : Single<List<Cat>> {
        return api.getDataList(Constants.API_KEY)
    }*/
}