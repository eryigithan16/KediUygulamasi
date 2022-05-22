package com.example.catapp.data.remote

import com.example.catapp.api.CatsRemoteApi
import com.example.catapp.common.Constants
import com.example.catapp.common.Constants.Companion.API_KEY
import com.example.catapp.data.Cat
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatRemoteDataSource
@Inject constructor(private val catsRemoteApi: CatsRemoteApi) {
    suspend fun getCatListDataFromApi() : List<Cat> {

        return catsRemoteApi.getDataList(API_KEY)
    }   //single gelen datayı list<cat> olark burda al return et
        //return catsRemoteApi.getDataList(Constants.API_KEY) // return yerine bir değişkene atayıp sadeleştir. List<Cat> elde et
        //return catsRemoteApi.getDataList(API_KEY)
}