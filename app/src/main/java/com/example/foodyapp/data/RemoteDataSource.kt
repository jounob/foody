package com.example.foodyapp.data

import com.example.foodyapp.data.network.FoodRecipesApi
import com.example.foodyapp.models.recipes.FoodRecipeDM
import javax.inject.Inject
import retrofit2.Response

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipeDM> {
        return foodRecipesApi.getRecipes(queries)
    }
}