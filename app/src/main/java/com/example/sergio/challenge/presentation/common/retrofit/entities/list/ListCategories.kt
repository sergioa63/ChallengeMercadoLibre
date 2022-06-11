package com.example.sergio.challenge.presentation.common.retrofit.entities.list

import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.ResponceListCategories
import retrofit2.http.GET
import retrofit2.http.Url

interface ListCategories {
    @GET
    suspend fun getCategories(@Url url : String) : retrofit2.Response<ResponceListCategories>
}