package com.nankung.common.module.extension.view

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.nankung.common.R
import com.nankung.common.module.glide.GlideApp

/**
  * Created by「 Nan Kung 」on 09 MARCH 2020 ^^
 */

// Normal ImageView Loading in Background Thread

fun AppCompatImageView.setGlideImage(@DrawableRes id: Int, cache: DiskCacheStrategy = DiskCacheStrategy.ALL) {
    GlideApp.with(this.context)
        .load(id)
        .placeholder(R.drawable.placehoder)
        .diskCacheStrategy(cache)
        .into(this)
}

fun AppCompatImageView.setGlideImage(url: String?,cache: DiskCacheStrategy = DiskCacheStrategy.ALL) {
    GlideApp.with(this.context)
        .load(url)
        .placeholder(R.drawable.placehoder)
        .diskCacheStrategy(cache)
        .into(this)
}

fun AppCompatImageView.setGlideImage(bitmap: Bitmap?, cache: DiskCacheStrategy = DiskCacheStrategy.ALL) {
    GlideApp.with(this.context)
        .load(bitmap)
        .placeholder(R.drawable.placehoder)
        .diskCacheStrategy(cache)
        .into(this)
}

// Circle ImageView Loading in Background Thread
fun AppCompatImageView.setCircleImage(@DrawableRes id: Int, cache: DiskCacheStrategy = DiskCacheStrategy.ALL) {
    GlideApp.with(this.context)
        .load(id)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.placehoder)
        .diskCacheStrategy(cache)
        .into(this)
}

fun AppCompatImageView.setCircleImage(url: String?, cache: DiskCacheStrategy = DiskCacheStrategy.ALL) {
    GlideApp.with(this.context)
        .load(url)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.placehoder)
        .diskCacheStrategy(cache)
        .into(this)
}

fun AppCompatImageView.setCircleImage(bitmap: Bitmap?, cache: DiskCacheStrategy = DiskCacheStrategy.ALL) {
    GlideApp.with(this.context)
        .load(bitmap)
        .apply(RequestOptions.circleCropTransform())
        .placeholder(R.drawable.placehoder)
        .diskCacheStrategy(cache)
        .into(this)
}



