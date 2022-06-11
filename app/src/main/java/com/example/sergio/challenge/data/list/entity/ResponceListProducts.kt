package com.example.sergio.challenge.data.list.entity

import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.Results

data class ResponceListProducts(val isSuccess : Boolean, val msnError : String?, val list : List<Results> = emptyList())
