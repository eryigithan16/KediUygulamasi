package com.example.catapp.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catapp.R
import com.example.catapp.data.model.Cat
import com.example.catapp.ui.home.HomeCatsAdapter
import com.example.catapp.ui.home.HomeFragmentDirections


fun ImageView.getImageFromUrl(url: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

fun placeholderProgessBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}


@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url:String?) {
    view.getImageFromUrl(url, placeholderProgessBar(view.context))
}
