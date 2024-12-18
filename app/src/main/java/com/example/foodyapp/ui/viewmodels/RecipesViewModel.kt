package com.example.foodyapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodyapp.util.Constants
import com.example.foodyapp.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.example.foodyapp.util.Constants.Companion.QUERY_API_KEY
import com.example.foodyapp.util.Constants.Companion.QUERY_DIET
import com.example.foodyapp.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.example.foodyapp.util.Constants.Companion.QUERY_NUMBER
import com.example.foodyapp.util.Constants.Companion.QUERY_TYPE

class RecipesViewModel : ViewModel() {
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }
}