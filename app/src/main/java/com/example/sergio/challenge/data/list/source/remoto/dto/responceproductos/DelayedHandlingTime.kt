package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class DelayedHandlingTime (

  @SerializedName("period" ) var period : String? = null,
  @SerializedName("rate"   ) var rate   : Int?    = null,
  @SerializedName("value"  ) var value  : Int?    = null

)