package com.example.foodyapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: RecipesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY ID ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>
}