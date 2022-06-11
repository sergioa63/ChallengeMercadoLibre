package com.example.sergio.challenge.data.list.source.remoto.dto.responcedetalle

import com.google.gson.annotations.SerializedName


data class ResponceDetalleProduct (

  @SerializedName("text"         ) var text        : String?   = null,
  @SerializedName("plain_text"   ) var plainText   : String?   = null,
  @SerializedName("last_updated" ) var lastUpdated : String?   = null,
  @SerializedName("date_created" ) var dateCreated : String?   = null,
  @SerializedName("snapshot"     ) var snapshot    : Snapshot? = Snapshot()

)