package com.example.catapp.domain

import com.example.catapp.api.CatsRemoteApi
import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
import com.example.catapp.data.remote.CatRepository
import javax.inject.Inject


class FavCatComparisonUseCase
    @Inject constructor(private val remoteRepository: CatRepository, private val localRepository: CatLocalRepository) {

    suspend operator fun invoke() : List<Cat> {
        val localResponse = localRepository.getAllCatsFromLocal()
        val remoteResponse = remoteRepository.getAllCats()

        remoteResponse.forEachIndexed { index, element ->
            localResponse.forEachIndexed { index2, element2 ->
                if (localResponse[index2].catName.equals(remoteResponse[index].catName)) {
                    remoteResponse[index].catIsFavorited = true
                }
            }
        }
        return remoteResponse
    }

}