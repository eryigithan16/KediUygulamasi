package com.example.catapp.data.remote

import com.example.catapp.data.Cat
import javax.inject.Inject

class CatRepositoryImpl
    @Inject constructor(private val catRemoteDataSource: CatRemoteDataSource) : CatRepository {
    override suspend fun getAllCats() = catRemoteDataSource.getCatListDataFromApi()
}