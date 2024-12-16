package com.example.foodyapp.data.database

import androidx.room.TypeConverter
import com.example.foodyapp.models.recipes.FoodRecipeDM
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun foodRecipesToString(foodRecipeDM: FoodRecipeDM): String {
        return gson.toJson(foodRecipeDM)
    }

    @TypeConverter
    fun stringToFoodRecipes(data: String): FoodRecipeDM {
        val listType = object : TypeToken<FoodRecipeDM>() {}.type

        return gson.fromJson(data, listType)
    }
}