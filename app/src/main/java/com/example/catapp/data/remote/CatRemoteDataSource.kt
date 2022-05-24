package com.example.catapp.data.remote

import com.example.catapp.data.model.Cat

interface CatRemoteDataSource {
    suspend fun getCatListDataFromApi() : List<Cat>
}