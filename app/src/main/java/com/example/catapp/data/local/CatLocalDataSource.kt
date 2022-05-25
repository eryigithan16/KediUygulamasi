package com.example.catapp.data.local

import com.example.catapp.data.model.Cat

interface CatLocalDataSource {
    suspend fun getCatListDataFromLocal() : List<Cat>

    suspend fun insertCatToLocal(cat: Cat)

    suspend fun deleteCatFromLocal(name : String)
}