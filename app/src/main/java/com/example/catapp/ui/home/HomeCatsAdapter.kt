package com.example.catapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.R
import com.example.catapp.data.Cat
import com.example.catapp.databinding.CatsItemBinding

class HomeCatsAdapter : ListAdapter<Cat, HomeCatsAdapter.CatsViewHolder>(HomeCatsDiffCallback()) {

    class CatsViewHolder(var view: CatsItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CatsItemBinding>(inflater, R.layout.cats_item, parent,false)
        return CatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.view.cat = getItem(position)
    }
}

class HomeCatsDiffCallback : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.catName == newItem.catName
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}
