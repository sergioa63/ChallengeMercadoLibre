package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Conditions (

  @SerializedName("context_restrictions" ) var contextRestrictions : ArrayList<String> = arrayListOf(),
  @SerializedName("start_time"           ) var startTime           : String?           = null,
  @SerializedName("end_time"             ) var endTime             : String?           = null,
  @SerializedName("eligible"             ) var eligible            : Boolean?          = null

)