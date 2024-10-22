package com.example.foodyapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodyapp.data.Repository
import com.example.foodyapp.data.network.ConnectionHelper
import com.example.foodyapp.models.recipes.FoodRecipeDM
import com.example.foodyapp.ui.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.Response

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    private val connectionHelper: ConnectionHelper
) : ViewModel() {
     var recipesResponse: MutableLiveData<NetworkResult<FoodRecipeDM>> = MutableLiveData()
//    val _recipesResponse: LiveData<NetworkResult<FoodRecipeDM>> = recipesResponse

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = NetworkResult.Loading()
        if (connectionHelper.hasInternetConnection()) {
            try {
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)
            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error("Recipes not found")
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet Connection")
        }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipeDM>): NetworkResult<FoodRecipeDM> {
        return when {
            response.message().toString().contains("timeout") -> NetworkResult.Error("Timeout")
            response.code() == 42 -> NetworkResult.Error("API Key Limited.")
            response.body()?.results.isNullOrEmpty() -> NetworkResult.Error("Recipes not found")
            response.isSuccessful -> {
                val foodRecipes = response.body()
                NetworkResult.Success(foodRecipes!!)
            }

            else -> NetworkResult.Error(response.message())
        }
    }
}