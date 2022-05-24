package com.example.catapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.catapp.R
import com.example.catapp.data.model.Cat
import com.example.catapp.databinding.CatsItemBinding

class HomeCatsAdapter(val onFavouriteChanged : (String?, Boolean?) -> Unit) : ListAdapter<Cat, HomeCatsAdapter.CatsViewHolder>(HomeCatsDiffCallback()) {

    class CatsViewHolder(var view: CatsItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CatsItemBinding>(inflater, R.layout.cats_item, parent,false)
        return CatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        val item = getItem(position)
        holder.view.cat = item
        if (item.catIsFavorited == true) {
            holder.view.ivItemAddFavBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        }
        else {
            holder.view.ivItemAddFavBtn.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
        holder.view.ivItemAddFavBtn.setOnClickListener {
            onFavouriteChanged(item.catName,item.catIsFavorited)
            holder.view.executePendingBindings()
        }
        holder.view.executePendingBindings()
    }
}

class HomeCatsDiffCallback : DiffUtil.ItemCallback<Cat>() {
    override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return oldItem.catName == newItem.catName && oldItem.catIsFavorited == newItem.catIsFavorited
    }

    override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
        return areItemsTheSame(oldItem,newItem)
    }

}
