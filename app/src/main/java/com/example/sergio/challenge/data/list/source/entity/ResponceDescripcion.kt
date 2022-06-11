package com.example.sergio.challenge.data.list.source.entity

import com.example.sergio.challenge.data.list.source.remoto.dto.responcedetalle.ResponceDetalleProduct

data class ResponceDescripcion(val isSuccess : Pair<Boolean,String>, val resp : ResponceDetalleProduct?)
