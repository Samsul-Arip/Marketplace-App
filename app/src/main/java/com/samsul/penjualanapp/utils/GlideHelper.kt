package com.samsul.penjualanapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.samsul.penjualanapp.R

class GlideHelper {

    companion object {
        fun setImage(context : Context, urlImage : String, imageView: ImageView){
            Glide.with(context)
                .load(urlImage)
                .centerCrop()
                .placeholder(R.drawable.img_no_image)
                .error(R.drawable.img_no_image)
                .into(imageView)
        }
    }
}