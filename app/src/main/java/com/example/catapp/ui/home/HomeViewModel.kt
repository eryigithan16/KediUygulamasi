package com.example.catapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
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

    private val _cats = MutableStateFlow(CatsUiState())
    val cats : StateFlow<CatsUiState> = _cats.asStateFlow()

    fun getListFromRemoteRepo(){
        viewModelScope.launch {
            val catsItems = remoteRepository.getAllCats()
            _cats.update {
                it.copy(catsItems = catsItems)
            }
        }
    }

    fun saveToRoom(list: List<Cat>){
        viewModelScope.launch {
            val listLong = localRepository.storeCatsToLocal(*list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].catId = listLong[i].toInt()
                i = i+1
            }
        }
    }

    fun getListFromLocalRepo(){
        viewModelScope.launch {
            val cats = localRepository.getAllCatsFromLocal()
            Log.e("@@@@@room",cats.toString())
        }
    }

}