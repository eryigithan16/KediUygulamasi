package com.example.catapp.data.remote

import com.example.catapp.api.CatsRemoteApi
import com.example.catapp.common.Constants
import com.example.catapp.common.Constants.Companion.API_KEY
import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatRemoteDataSourceImpl
@Inject constructor(private val catsRemoteApi: CatsRemoteApi) : CatRemoteDataSource
{
    override suspend fun getCatListDataFromApi(): List<Cat> {
        return catsRemoteApi.getDataList(API_KEY)
    }
}