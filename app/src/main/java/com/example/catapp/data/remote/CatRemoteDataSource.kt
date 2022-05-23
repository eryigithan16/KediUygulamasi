package com.example.catapp.data.remote

import com.example.catapp.api.CatsRemoteApi
import com.example.catapp.common.Constants.Companion.API_KEY
import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatRemoteDataSource
@Inject constructor(private val catsRemoteApi: CatsRemoteApi) {
    suspend fun getCatListDataFromApi() : List<Cat> {
        return catsRemoteApi.getDataList(API_KEY)
    }
}