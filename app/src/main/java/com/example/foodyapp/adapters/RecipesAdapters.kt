package com.example.foodyapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodyapp.binding_adapters.RecipesRowBinding.Companion.applyVeganColor
import com.example.foodyapp.binding_adapters.RecipesRowBinding.Companion.loadImageView
import com.example.foodyapp.databinding.RecipesRowLayoutBinding
import com.example.foodyapp.models.recipes.FoodRecipeDM
import com.example.foodyapp.models.recipes.Result
import com.example.foodyapp.util.RecipesDiffUtil

class RecipesAdapters : RecyclerView.Adapter<RecipesAdapters.RecipesViewHolder>() {

    private var recipes = emptyList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        return RecipesViewHolder(
            RecipesRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    fun setData(newData: FoodRecipeDM) {
        val recipesDiffUtil = RecipesDiffUtil(recipes, newData.results)
        val diffUtilsResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        Log.d("RecipesFragmentAdapterRecipes", "$recipes.")
        diffUtilsResult.dispatchUpdatesTo(this)
    }

    class RecipesViewHolder(private val binding: RecipesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.apply {
                loadImageView(recipeIV, result.image)
                recipeTitleTV.text = result.title
                recipeDescripeTV.text = result.summary
                heartTV.text = result.aggregateLikes.toString()
                clockTV.text = result.readyInMinutes.toString()
                applyVeganColor(leafTV, result.vegan)
                applyVeganColor(leafIV, result.vegan)
            }
        }
    }
}