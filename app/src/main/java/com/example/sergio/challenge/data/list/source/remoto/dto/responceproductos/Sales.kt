package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Sales (

  @SerializedName("period"    ) var period    : String? = null,
  @SerializedName("completed" ) var completed : Int?    = null

)