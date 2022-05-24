package com.example.catapp.data.local

import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatLocalRepositoryImpl
    @Inject constructor(private val catLocalDataSource: CatLocalDataSource): CatLocalRepository {
    override suspend fun getAllCatsFromLocal(): List<Cat> {
        return catLocalDataSource.getCatListDataFromLocal()
    }

    override suspend fun storeCatsToLocal(cat: Cat) = catLocalDataSource.insertAllCatsToLocal(cat)
}