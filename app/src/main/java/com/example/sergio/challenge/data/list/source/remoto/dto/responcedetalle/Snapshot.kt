package com.example.sergio.challenge.data.list.source.remoto.dto.responcedetalle

import com.google.gson.annotations.SerializedName


data class Snapshot (

  @SerializedName("url"    ) var url    : String? = null,
  @SerializedName("width"  ) var width  : Int?    = null,
  @SerializedName("height" ) var height : Int?    = null,
  @SerializedName("status" ) var status : String? = null

)