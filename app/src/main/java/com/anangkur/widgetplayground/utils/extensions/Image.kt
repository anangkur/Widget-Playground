package com.anangkur.widgetplayground.utils.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(image: String) {
    Picasso.get().load(image).into(this)
}