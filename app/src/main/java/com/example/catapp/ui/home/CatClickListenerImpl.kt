package com.example.catapp.ui.home

import android.view.View
import androidx.navigation.Navigation
import com.example.catapp.data.model.Cat

class CatClickListenerImpl(var view: View) : CatClickListener {
    override fun onItemClick(cat: Cat) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(cat)
        Navigation.findNavController(view).navigate(action)
    }
}