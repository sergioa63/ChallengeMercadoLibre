package com.example.sergio.challenge.presentation.common.retrofit

import android.content.Context
import android.util.Pair
import com.example.sergio.challenge.presentation.common.retrofit.entities.list.ListCategories
import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.ResponceListCategories
import com.example.sergio.challenge.data.list.source.remoto.dto.responcedetalle.ResponceDetalleProduct
import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.ResponceProductos
import com.example.sergio.challenge.presentation.common.retrofit.entities.list.DescriptProduct
import com.example.sergio.challenge.presentation.common.retrofit.entities.list.ProductosList
import java.net.SocketTimeoutException

class WebServiceHandler private constructor(val context : Context) {
    companion object {
        private var INSTANCE: WebServiceHandler? = null
        fun getInstance(
            context: Context
        ): WebServiceHandler =
            INSTANCE ?: WebServiceHandler(context)
    }
    var httpHandler : RetrofitHandler = RetrofitHandler(context)
    private val retrofitListCategories  by lazy {  httpHandler.getRetrofit()!!.create(ListCategories::class.java) }
    private val retrofitListProductos by lazy {  httpHandler.getRetrofit()!!.create(ProductosList::class.java) }
    private val retrofitDescripcionProduct by lazy {  httpHandler.getRetrofit()!!.create(DescriptProduct::class.java) }

    suspend fun categoriasConsulta() : Pair<String?, ResponceListCategories?>{
        return try {
            val responce =
                retrofitListCategories.getCategories("https://api.mercadolibre.com/sites/MCO")
            if (responce.isSuccessful) {
                responce.body()?.let {
                    return Pair(null,it)
                }
            }
            return Pair("Not found categories", null);
        }  catch (e: Exception) {
            return if (e is SocketTimeoutException) {
                Pair("La solicitud supero el tiempo maximo de espera ", null)
            } else {
                Pair(e.message, null)
            }
        }
    }

    suspend fun consultaByCodeConsulta (code : String) : Pair<String?, ResponceProductos?>{
        return try {
            val responce =
                retrofitListProductos.
                getProductos("https://api.mercadolibre.com/sites/MCO/search?category=$code")
            if (responce.isSuccessful) {
                responce.body()?.let {
                    return Pair(null,it)
                }
            }
            return Pair("Not found categories", null);
        }  catch (e: Exception) {
            return if (e is SocketTimeoutException) {
                Pair("La solicitud supero el tiempo maximo de espera ", null)
            } else {
                Pair(e.message, null)
            }
        }
    }

    suspend fun consultaByCodeDescripcion (code : String) : Pair<String?, ResponceDetalleProduct?>{
        return try {
            val responce =
                retrofitDescripcionProduct.
                getDescripcion("https://api.mercadolibre.com/items/$code/description#json")
            if (responce.isSuccessful) {
                responce.body()?.let {
                    return Pair(null,it)
                }
            }
            return Pair("Not found categories", null);
        }  catch (e: Exception) {
            return if (e is SocketTimeoutException) {
                Pair("La solicitud supero el tiempo maximo de espera ", null)
            } else {
                Pair(e.message, null)
            }
        }
    }
}