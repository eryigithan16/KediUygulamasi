package com.example.catapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.data.model.CatsUiState
import com.example.catapp.data.remote.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val repository: CatRepository) : ViewModel() {

    private val _cats = MutableStateFlow(CatsUiState())
    val cats : StateFlow<CatsUiState> = _cats.asStateFlow()

    fun getListfromRepo(){
        viewModelScope.launch {
            val catsItems = repository.getAllCats()
            _cats.update {
                it.copy(catsItems = catsItems)
            }
        }
    }

}