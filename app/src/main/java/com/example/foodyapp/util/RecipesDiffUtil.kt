package com.example.foodyapp.util

import androidx.recyclerview.widget.DiffUtil
import com.example.foodyapp.models.recipes.Result

class RecipesDiffUtil(
    private val oldList: List<Result>,
    private val newList: List<Result>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
