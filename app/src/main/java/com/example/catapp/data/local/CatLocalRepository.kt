package com.example.catapp.data.local

import com.example.catapp.data.model.Cat

interface CatLocalRepository {
    suspend fun getAllCatsFromLocal() : List<Cat>
    suspend fun storeCatsToLocal(vararg cats: Cat) : List<Long>
}