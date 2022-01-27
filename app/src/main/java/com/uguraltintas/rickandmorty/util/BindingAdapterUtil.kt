package com.uguraltintas.rickandmorty.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setImage")
fun ImageView.setImage(imageUrl: String?) {
    Glide
        .with(this)
        .load(imageUrl)
        .centerCrop()
        .into(this);
}

@BindingAdapter("setVisibility")
fun View.setVisibility(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.GONE
}