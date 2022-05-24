package com.example.catapp.data.remote

import com.example.catapp.api.CatsRemoteApi
import com.example.catapp.common.Constants
import com.example.catapp.common.Constants.Companion.API_KEY
import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
import javax.inject.Inject

class CatRemoteDataSourceImpl
@Inject constructor(private val catsRemoteApi: CatsRemoteApi, private val localRepository: CatLocalRepository) : CatRemoteDataSource
{
    override suspend fun getCatListDataFromApi(): List<Cat> {
        val localResponse : List<Cat> = localRepository.getAllCatsFromLocal()
        val remoteResponse : List<Cat> = catsRemoteApi.getDataList(API_KEY)
        //val list : ArrayList<Breed> = ArrayList()
        // val difference = remoteResponse.minus(localResponse).toList()

        remoteResponse.forEachIndexed { index, element ->
            localResponse.forEachIndexed { index2, element2 ->
                if (localResponse[index2].catName.equals(remoteResponse[index].catName)) {
                    remoteResponse[index].catIsFavorited = true

                }
            }
        }

        // difference.forEach { it.IsCatliked = false }
        //list.addAll(localResponse) // isliked true
        //list.addAll(difference) // is liked false

        return remoteResponse
    }
}