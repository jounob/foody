package com.example.foodyapp.binding_adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.bumptech.glide.Glide
import com.example.foodyapp.R

class RecipesRowBinding {
    companion object {
        fun applyVeganColor(view: View, isVegan: Boolean) {
            if (isVegan) {
                when (view) {
                    is TextView -> view.setTextColor(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )

                    is ImageView -> view.setColorFilter(
                        ContextCompat.getColor(
                            view.context,
                            R.color.green
                        )
                    )
                }
            }
        }

        fun setImage(view: ImageView, url: String) {
            Glide.with(view).load(url).into(view)
        }

        fun loadImageView(imageView: ImageView, url: String) {
            imageView.load(url) {
                crossfade(600)
            }
        }
    }
}