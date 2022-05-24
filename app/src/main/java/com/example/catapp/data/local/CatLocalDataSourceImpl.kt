package com.example.catapp.data.local

import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatLocalDataSourceImpl @Inject constructor(private val catDao: CatDao):CatLocalDataSource {

    override suspend fun getCatListDataFromLocal(): List<Cat> {
        return catDao.getAllCats()
    }

    override suspend fun insertAllCatsToLocal(cat: Cat) = catDao.insertToFavorites(cat)
}