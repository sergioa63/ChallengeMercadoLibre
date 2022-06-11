package com.example.sergio.challenge.presentation.common.retrofit.entities.list

import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.ResponceListCategories
import com.example.sergio.challenge.data.list.source.remoto.dto.responcedetalle.ResponceDetalleProduct
import retrofit2.http.GET
import retrofit2.http.Url

interface DescriptProduct {
    @GET
    suspend fun getDescripcion(@Url url : String) : retrofit2.Response<ResponceDetalleProduct>
}