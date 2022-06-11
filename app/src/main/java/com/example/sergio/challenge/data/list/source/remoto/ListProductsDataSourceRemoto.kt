package com.example.sergio.challenge.data.list.source.remoto

import com.example.sergio.challenge.data.list.source.entity.ResponceCategoria
import com.example.sergio.challenge.data.list.source.entity.ResponceDescripcion
import com.example.sergio.challenge.data.list.source.entity.ResponceListProductos
import com.example.sergio.challenge.presentation.common.retrofit.WebServiceHandler
import javax.inject.Inject

class ListProductsDataSourceRemoto @Inject constructor(private  val webServiceHandler : WebServiceHandler){

    suspend fun getListProductsWSCaregories () : ResponceCategoria {
        val responce = webServiceHandler.categoriasConsulta()
        return responce?.let { pair->
            (pair.first?.let {
                ResponceCategoria(Pair(false, it), emptyList())
            } ?: kotlin.run {
                pair.second?.let { responceListCategories ->
                    ResponceCategoria(Pair(true, ""), responceListCategories.categories)
                }
            })!!
        }
    }

    suspend fun consultaByCatalogo(codigo: String) : ResponceListProductos {
        val responce = webServiceHandler.consultaByCodeConsulta(codigo)
        return responce?.let { pair ->
            (pair.first?.let {
                ResponceListProductos(Pair(false, it), emptyList())
            } ?: kotlin.run {
                pair.second?.let { responceProductos ->
                    ResponceListProductos(Pair(true, ""), responceProductos.results)
                }
            })!!
        }
    }

    suspend fun consultarByDetalle(codigo: String) : ResponceDescripcion {
        val responce = webServiceHandler.consultaByCodeDescripcion(codigo)
        return responce?.let { pair ->
            (pair.first?.let {
                ResponceDescripcion(Pair(false, it), null)
            } ?: kotlin.run {
                pair.second?.let { responceProductos ->
                    ResponceDescripcion(Pair(true, ""), responceProductos)
                }
            })!!
        }

    }
}