package com.example.foodyapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodyapp.models.recipes.FoodRecipeDM
import com.example.foodyapp.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipeDM: FoodRecipeDM) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}