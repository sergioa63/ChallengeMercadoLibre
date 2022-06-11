package com.example.sergio.challenge.data.list.source.entity

import com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo.Categories

data class ResponceCategoria (val isSuccess : Pair<Boolean,String> , val list : List<Categories>)