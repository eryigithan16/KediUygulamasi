package com.example.catapp.data.local

import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatLocalDataSourceImpl @Inject constructor(private val catDao: CatDao):CatLocalDataSource {

    override suspend fun getCatListDataFromLocal(): List<Cat> = catDao.getAllCats()

    override suspend fun insertCatToLocal(cat: Cat) = catDao.insertToFavorites(cat)

    override suspend fun deleteCatFromLocal(name: String) = catDao.deleteFavCat(name)

}