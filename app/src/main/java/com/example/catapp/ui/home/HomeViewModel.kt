package com.example.catapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.local.CatLocalRepository
import com.example.catapp.data.model.Cat
import com.example.catapp.data.model.CatsUiState
import com.example.catapp.domain.FavCatComparisonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor (private val localRepository: CatLocalRepository,
                     private val favCatComparisonUseCase: FavCatComparisonUseCase)
    : ViewModel() {

    private val _cats = MutableStateFlow(CatsUiState(onFavouriteChanged = { id, isFavourited ->
        viewModelScope.launch {
            selectedCat(cats.value.catsItems, id!!)?.let {
                if (isFavourited != true){
                    Log.e("@@@@store", it.catName.toString())
                    localRepository.storeCatToLocal(it)
                }
                else if (isFavourited == true){
                    localRepository.deleteCat(id)
                    Log.e("@@@@delete", id)
                }
            }
            getListFromRemoteRepo()
        }
    }))
    val cats : StateFlow<CatsUiState> = _cats.asStateFlow()

    fun getListFromRemoteRepo(){
        viewModelScope.launch {
            val catsItems = favCatComparisonUseCase.invoke()
                _cats.update {
                it.copy(catsItems = catsItems)
            }
        }
    }


    private fun selectedCat(catList: List<Cat>, callId: String) : Cat?{
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