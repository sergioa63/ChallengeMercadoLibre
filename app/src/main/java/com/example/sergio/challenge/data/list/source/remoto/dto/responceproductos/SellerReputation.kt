package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class SellerReputation (

  @SerializedName("power_seller_status" ) var powerSellerStatus : String?       = null,
  @SerializedName("level_id"            ) var levelId           : String?       = null,
  @SerializedName("metrics"             ) var metrics           : Metrics?      = Metrics(),
  @SerializedName("transactions"        ) var transactions      : Transactions? = Transactions()

)