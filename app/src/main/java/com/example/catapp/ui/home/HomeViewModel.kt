package com.example.catapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.Cat
import com.example.catapp.data.remote.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val repository: CatRepository) : ViewModel() {

    private val _cats = MutableLiveData<List<Cat>>()
    val cats : LiveData<List<Cat>> get() = _cats

    /*val catRemoteDataSource = CatRemoteDataSource(CatsRemoteService().api)
    val catRepository = CatRepositoryImpl(catRemoteDataSource)

     */

    fun getListfromRepo(){
        viewModelScope.launch {
            val response = repository.getAllCats()
            Log.e("@@@@",response.toString())
        }
    }

    /*data class CatsUiState(
        val catsItems : List<CatItemUiState> = listOf()
    )
    data class CatItemUiState(
        val name: String,
        val image: String,
        val favorited: Boolean = false
    )*/

}

/*data class CatsUiState(
    val catsItems : List<CatItemUiState> = listOf()
)
data class CatItemUiState(
    val name: String,
    val image: String,
    val favorited: Boolean = false
)*/