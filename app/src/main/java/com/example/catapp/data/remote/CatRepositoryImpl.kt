package com.example.catapp.data.remote

import javax.inject.Inject

class CatRepositoryImpl
    @Inject constructor(private val catRemoteDataSource: CatRemoteDataSource) : CatRepository {

    override suspend fun getAllCats() = catRemoteDataSource.getCatListDataFromApi()

}