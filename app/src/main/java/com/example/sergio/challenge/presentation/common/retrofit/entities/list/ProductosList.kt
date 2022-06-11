package com.example.sergio.challenge.presentation.common.retrofit.entities.list

import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.ResponceProductos
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductosList {
    @GET
    suspend fun getProductos(@Url url : String) : retrofit2.Response<ResponceProductos>
}