package com.example.foodyapp.models.recipes


import com.google.gson.annotations.SerializedName

data class FoodRecipeDM(
    @SerializedName("results")
    val results: List<Result>,
)