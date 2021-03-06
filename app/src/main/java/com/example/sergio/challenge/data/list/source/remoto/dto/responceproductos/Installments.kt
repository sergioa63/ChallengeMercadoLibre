package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Installments (

  @SerializedName("quantity"    ) var quantity   : Int?    = null,
  @SerializedName("amount"      ) var amount     : Double? = null,
  @SerializedName("rate"        ) var rate       : Int?    = null,
  @SerializedName("currency_id" ) var currencyId : String? = null

)