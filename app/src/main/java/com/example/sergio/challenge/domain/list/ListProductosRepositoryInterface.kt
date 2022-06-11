package com.example.sergio.challenge.domain.list

import com.example.sergio.challenge.data.list.entity.ResponceCatalogo
import com.example.sergio.challenge.data.list.entity.ResponceListProducts
import com.example.sergio.challenge.data.list.source.entity.ResponceDescripcion
import com.example.sergio.challenge.data.list.source.entity.ResponceListProductos
import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.Categories
import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.Results

interface ListProductosRepositoryInterface {
    suspend fun saveListCatalogo() : ResponceCatalogo

    suspend fun getListCatalogo() : List<Categories>

    suspend fun consultaByCatalogo (codigo : String) : ResponceListProducts

    suspend fun getListProducts(code : String) :  List<Results>

    suspend fun consultarDetalle(code: String) : ResponceDescripcion
}