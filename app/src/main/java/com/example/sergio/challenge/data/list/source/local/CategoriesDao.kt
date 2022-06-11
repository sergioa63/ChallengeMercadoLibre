package com.example.sergio.challenge.data.list.source.local

import androidx.room.*
import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.Categories


@Dao
interface CategoriesDao {

    @Query("SELECT * FROM Categories")
    fun getAllCategories(): List<Categories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(order: List<Categories?>?)

    @Insert
    fun insertCategories(categories: Categories)

    @Update
    fun updateCategories(categories: Categories)

    @Delete
    fun deleteCategories(categories: Categories)

    @Query("DELETE FROM Categories")
    fun clearCategories()
}