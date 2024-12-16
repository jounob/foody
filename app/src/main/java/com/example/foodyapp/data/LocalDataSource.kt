package com.example.foodyapp.data

import com.example.foodyapp.data.database.RecipesDao
import com.example.foodyapp.data.database.RecipesEntity
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {
    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipes: RecipesEntity) {
        recipesDao.insertRecipes(recipes)
    }
}