package com.example.catapp.ui.home

import com.example.catapp.data.model.Cat

interface CatClickListener {
    fun onItemClick(cat: Cat)
}