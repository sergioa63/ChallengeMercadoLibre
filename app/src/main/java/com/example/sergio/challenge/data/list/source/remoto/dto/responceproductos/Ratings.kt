package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Ratings (

  @SerializedName("negative" ) var negative : Double? = null,
  @SerializedName("neutral"  ) var neutral  : Double? = null,
  @SerializedName("positive" ) var positive : Double? = null

)