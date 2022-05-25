package com.example.catapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
import com.example.catapp.data.model.CatImage
import com.example.catapp.data.model.CatsUiState
import com.example.catapp.data.remote.CatRepository
import com.example.catapp.domain.FavCatComparisonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor (private val remoteRepository: CatRepository, private val localRepository: CatLocalRepository,
                     private val favCatComparisonUseCase: FavCatComparisonUseCase) : ViewModel() {

    private val _cats = MutableStateFlow(CatsUiState(onFavouriteChanged = { id, isFavourited ->
        viewModelScope.launch {
            Log.e("@@@@deneme","$id, ${isFavourited.toString()}")
            selectedCat(cats.value.catsItems, id!!)?.let {
                if (isFavourited != true){
                    localRepository.storeCatsToLocal(it)
                }
                else if (isFavourited == true){
                    localRepository.deleteCat(id)
                }
            }
            getListFromRemoteRepo()
        }
    }))
    val cats : StateFlow<CatsUiState> = _cats.asStateFlow()

    fun getListFromRemoteRepo(){
        viewModelScope.launch {
            //val catsItems = remoteRepository.getAllCats()
            val catsItems = favCatComparisonUseCase.invoke()
                _cats.update {
                it.copy(catsItems = catsItems)
            }
        }
    }

    fun getListFromLocalRepo(){
        viewModelScope.launch {
            val cats = localRepository.getAllCatsFromLocal()
            Log.e("@@@@@room",cats.toString())
        }
    }

    fun selectedCat(catList: List<Cat>, callId: String) : Cat?{
        var i = 0
        while (i<catList.size){
            if (catList[i].catName.equals(callId)){
                return catList[i]
            }
            i++
        }
        return null
    }

}