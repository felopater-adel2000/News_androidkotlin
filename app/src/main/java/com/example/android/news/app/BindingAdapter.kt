package com.example.android.news.app

import android.opengl.Visibility
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.android.news.R

@BindingAdapter("visbilityState")
fun ProgressBar.visbilityState(state: State)
{
    when(state)
    {
        State.Loading -> {
            this.visibility = View.VISIBLE
        }
        else -> {
            Log.i("Felo", "else branch")
            this.visibility = View.GONE
        }
    }

}

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?)
{
    printMessage(url!!)
    val notNullUrl = if(url.isNullOrBlank() || url.isNullOrEmpty() || url == "null"){"https://cdn1.vectorstock.com/i/1000x1000/31/20/image-error-icon-editable-outline-vector-30393120.jpg"} else {url}

    val imgUrl = notNullUrl.toUri().buildUpon().scheme("https").build()
    Glide.with(this.context).load(imgUrl).into(this)
}