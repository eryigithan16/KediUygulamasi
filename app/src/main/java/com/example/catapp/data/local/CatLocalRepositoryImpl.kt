package com.example.catapp.data.local

import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatLocalRepositoryImpl
    @Inject constructor(private val catLocalDataSource: CatLocalDataSource): CatLocalRepository {

    override suspend fun getAllCatsFromLocal(): List<Cat> = catLocalDataSource.getCatListDataFromLocal()

    override suspend fun storeCatToLocal(cat: Cat) = catLocalDataSource.insertCatToLocal(cat)

    override suspend fun deleteCat(name: String) = catLocalDataSource.deleteCatFromLocal(name)

}