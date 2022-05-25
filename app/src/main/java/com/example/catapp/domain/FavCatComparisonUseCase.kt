package com.example.catapp.domain

import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
import com.example.catapp.data.remote.CatRepository
import javax.inject.Inject


class FavCatComparisonUseCase
    @Inject constructor(private val remoteRepository: CatRepository, private val localRepository: CatLocalRepository) {

    suspend operator fun invoke() : List<Cat> {
        val localResponse = localRepository.getAllCatsFromLocal()
        val remoteResponse = remoteRepository.getAllCats()

        remoteResponse.forEachIndexed { i, element ->
            localResponse.forEachIndexed { j, element2 ->
                if (localResponse[j].catName.equals(remoteResponse[i].catName)) {
                    remoteResponse[i].catIsFavorited = true
                }
            }
        }
        return remoteResponse
    }

}