package com.example.sergio.challenge.data.list.source.entity

import com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos.Results

data class ResponceListProductos(val isSuccess : Pair<Boolean,String>, val list : List<Results>)
