package com.example.foodyapp.binding_adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import coil.load
import com.bumptech.glide.Glide
import com.example.foodyapp.R
import com.example.foodyapp.data.database.RecipesEntity
import com.example.foodyapp.models.recipes.FoodRecipeDM
import com.example.foodyapp.ui.NetworkResult

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
                error(R.drawable.ic_error_placeholder)
            }
        }

        fun errorImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipeDM>?,
            database: List<RecipesEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else if (apiResponse is NetworkResult.Loading) {
                imageView.visibility = View.INVISIBLE
            } else if (apiResponse is NetworkResult.Success) {
                imageView.visibility = View.INVISIBLE
            }
        }

        fun errorTextViewVisibility(
            textView: TextView,
            apiResponse: NetworkResult<FoodRecipeDM>?,
            database: List<RecipesEntity>?
        ) {
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            } else if (apiResponse is NetworkResult.Loading) {
                textView.visibility = View.INVISIBLE
            } else if (apiResponse is NetworkResult.Success) {
                textView.visibility = View.INVISIBLE
            }
        }
    }
}