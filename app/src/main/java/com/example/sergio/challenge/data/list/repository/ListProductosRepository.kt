package com.example.sergio.challenge.data.list.repository

import com.example.sergio.challenge.data.list.entity.ResponceCatalogo
import com.example.sergio.challenge.data.list.entity.ResponceListProducts
import com.example.sergio.challenge.data.list.source.entity.ResponceDescripcion
import com.example.sergio.challenge.data.list.source.entity.ResponceListProductos
import com.example.sergio.challenge.data.list.source.local.ListProductsDataSourceLocal
import com.example.sergio.challenge.data.list.source.remoto.ListProductsDataSourceRemoto
import com.example.sergio.challenge.domain.list.ListProductosRepositoryInterface
import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.Categories
import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.Results
import javax.inject.Inject

class ListProductosRepository  @Inject constructor
    (private val listProductsDataSourceRemoto: ListProductsDataSourceRemoto,
    private val listProductsDataSourceLocal: ListProductsDataSourceLocal) : ListProductosRepositoryInterface {

    override suspend fun saveListCatalogo() : ResponceCatalogo {
        val existCatalogoDB = listProductsDataSourceLocal.getlistProductsDB()
        return existCatalogoDB?.let {
            if(it.isEmpty()) {
                val resp = listProductsDataSourceRemoto.getListProductsWSCaregories();
                if(resp.list.isNotEmpty()) {
                    listProductsDataSourceLocal.saveListCatalogo(resp.list)
                }
                ResponceCatalogo(resp.isSuccess.first, resp.isSuccess.second)
            }
            ResponceCatalogo(true, "")
        }
    }

    override suspend fun getListCatalogo(): List<Categories> {
        return listProductsDataSourceLocal.getlistProductsDB()
    }



    override suspend fun consultaByCatalogo(codigo: String) : ResponceListProducts {
        val resp = listProductsDataSourceRemoto.consultaByCatalogo(codigo)
        if(resp.list.isNotEmpty()) {
            listProductsDataSourceLocal.saveProductos(resp.list)
            return ResponceListProducts(resp.isSuccess.first, resp.isSuccess.second, resp.list)
        }
        return ResponceListProducts(resp.isSuccess.first, resp.isSuccess.second)
    }

    override suspend fun getListProducts(code : String): List<Results> {
        return listProductsDataSourceLocal.getProdutosByCategory(code)
    }

    override suspend fun consultarDetalle(code: String) : ResponceDescripcion {
        val resp = listProductsDataSourceRemoto.consultarByDetalle(code)
        resp?.let {
            return ResponceDescripcion(it.isSuccess, it.resp)
        }
    }
}