package com.example.catapp.data.remote

import com.example.catapp.data.model.Cat

interface CatRepository {
    suspend fun getAllCats() : List<Cat>
}