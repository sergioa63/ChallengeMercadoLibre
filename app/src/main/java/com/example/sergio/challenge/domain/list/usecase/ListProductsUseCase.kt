package com.example.sergio.challenge.domain.list.usecase

import com.example.sergio.challenge.R
import com.example.sergio.challenge.data.list.repository.ListProductosRepository
import com.example.sergio.challenge.domain.list.entity.Producto
import com.example.sergio.challenge.domain.list.entity.SuccessCatalogo
import com.example.sergio.challenge.domain.list.entity.SuccessDescripcionProduct
import com.example.sergio.challenge.domain.list.entity.SuccessProductos
import com.example.sergio.challenge.presentation.list.adapter.AutocompleteData
import java.util.*
import javax.inject.Inject

class ListProductsUseCase @Inject constructor(private val providerAListProductosRepository : ListProductosRepository) {

    suspend fun getDataWS() : SuccessCatalogo {
        val resp = providerAListProductosRepository.saveListCatalogo()
        return resp?.let {
            SuccessCatalogo(it.isSuccess, it.msnError)
        }
    }

    suspend fun consultarByCatalogo(codigo : String) : SuccessProductos{
        val responce = providerAListProductosRepository.consultaByCatalogo(codigo)
        return responce?.let {
            var listProduct : MutableList<Producto> = arrayListOf()
            it.list.forEach {
                listProduct.add(Producto(it.id,it.title, it.price, it.thumbnail))
            }
            SuccessProductos(it.isSuccess, it.msnError, listProduct.toList())
        }
    }

    suspend fun getListCatalogo() : List<AutocompleteData> {
        val resp = providerAListProductosRepository.getListCatalogo()
        var list = mutableListOf<AutocompleteData>()
        for (category in resp) {
            category?.let {
                it.name?.let { name ->
                    list.add(AutocompleteData(name, it.id))
                }
            }
        }
        return list.toList()
    }

    suspend fun consultarDetalle (codigo: String) : SuccessDescripcionProduct {
        val resp = providerAListProductosRepository.consultarDetalle(codigo)
        return resp?.let {
            it.resp?.let { detalle ->
                detalle.plainText?.let {  descrip ->
                    SuccessDescripcionProduct(it.isSuccess.first, it.isSuccess.second, descrip)
                }
            }
        } ?: kotlin.run {
            SuccessDescripcionProduct(resp.isSuccess.first, resp.isSuccess.second, "")
        }
    }

    suspend fun getListProductos(code : String) {
        val resp = providerAListProductosRepository.getListProducts(code)
    }
}