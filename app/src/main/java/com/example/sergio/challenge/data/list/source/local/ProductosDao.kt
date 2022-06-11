package com.example.sergio.challenge.data.list.source.local

import androidx.room.*
import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.Results

@Dao
interface ProductosDao {

    @Query("SELECT * FROM Results")
    fun getAllProductos(): List<Results>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProductos(order: List<Results?>?)

    @Query("SELECT * FROM Results WHERE categoryId = :category")
    fun getAllProductos(category : String): List<Results>

    @Insert
    fun insertProductos(results: Results)

    @Update
    fun updateProductos(results: Results)

    @Delete
    fun deleteProductos(results : Results)

    @Query("DELETE FROM Results")
    fun clearProductos()
}