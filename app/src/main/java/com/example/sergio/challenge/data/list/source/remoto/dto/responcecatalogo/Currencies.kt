package com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo

import com.google.gson.annotations.SerializedName


data class Currencies (

  @SerializedName("id"     ) var id     : String? = null,
  @SerializedName("symbol" ) var symbol : String? = null

)