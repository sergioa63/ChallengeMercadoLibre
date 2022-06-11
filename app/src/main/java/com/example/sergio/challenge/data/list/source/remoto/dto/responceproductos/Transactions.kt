package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Transactions (

  @SerializedName("canceled"  ) var canceled  : Int?     = null,
  @SerializedName("period"    ) var period    : String?  = null,
  @SerializedName("total"     ) var total     : Int?     = null,
  @SerializedName("ratings"   ) var ratings   : Ratings? = Ratings(),
  @SerializedName("completed" ) var completed : Int?     = null

)