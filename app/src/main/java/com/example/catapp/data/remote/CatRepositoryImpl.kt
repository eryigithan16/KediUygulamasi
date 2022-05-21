package com.example.catapp.data.remote

import com.example.catapp.data.Cat

class CatRepositoryImpl(private val catRemoteDataSource: CatRemoteDataSource) : CatRepository {
    override suspend fun getAllCats() = catRemoteDataSource.getCatListDataFromApi()
}