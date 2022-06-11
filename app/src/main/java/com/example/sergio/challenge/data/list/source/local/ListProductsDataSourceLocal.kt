package com.example.sergio.challenge.data.list.source.local

import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.Categories
import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.Results
import javax.inject.Inject

class ListProductsDataSourceLocal  @Inject constructor(private val categoriesDao: CategoriesDao,
                                                        private val productosDao : ProductosDao) {
    suspend fun saveListCatalogo (list : List<Categories>) {
        categoriesDao.insertAllCategories(list)
    }

    suspend fun getlistProductsDB() : List<Categories> {
       return  categoriesDao.getAllCategories()
    }

    suspend fun saveProductos (list: List<Results>) {
        productosDao.insertAllProductos(list)
    }

    suspend fun getProdutosByCategory(code : String): List<Results> {
        return productosDao.getAllProductos(code)?.let {
            productosDao.getAllProductos(code)
        }
    }
}