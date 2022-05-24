package com.example.catapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
import com.example.catapp.data.model.CatImage
import com.example.catapp.data.model.CatsUiState
import com.example.catapp.data.remote.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor (private val remoteRepository: CatRepository, private val localRepository: CatLocalRepository)
    : ViewModel() {


    private val _cats = MutableStateFlow(CatsUiState(onFavouriteChanged = { id, isFavourited ->
        val j = CatImage("url")
        val k = Cat(id,"sdfasdf","asdasd","asd","asdas","asdas","4",
            j,true)
        Log.d("home",id+" - "+isFavourited.toString())
        viewModelScope.launch {
            localRepository.storeCatsToLocal(k)
            Log.d("room",localRepository.getAllCatsFromLocal().toString())
            getListFromRemoteRepo()
        }
    }))
    val cats : StateFlow<CatsUiState> = _cats.asStateFlow()

    fun getListFromRemoteRepo(){
        viewModelScope.launch {
            val catsItems = remoteRepository.getAllCats()
            _cats.update {
                it.copy(catsItems = catsItems)
            }
        }
    }

    fun saveToRoom(cat: Cat){
        viewModelScope.launch {
            localRepository.storeCatsToLocal(cat)
        }
    }

    fun getListFromLocalRepo(){
        viewModelScope.launch {
            val cats = localRepository.getAllCatsFromLocal()
            Log.e("@@@@@room",cats.toString())
        }
    }

}