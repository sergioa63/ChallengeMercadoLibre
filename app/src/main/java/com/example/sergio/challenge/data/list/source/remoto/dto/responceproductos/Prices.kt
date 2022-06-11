package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Prices (

  @SerializedName("id"                    ) var id                  : String?                    = null,
  @SerializedName("prices"                ) var prices              : ArrayList<Prices>          = arrayListOf(),
  @SerializedName("presentation"          ) var presentation        : Presentation?              = Presentation(),
  @SerializedName("payment_method_prices" ) var paymentMethodPrices : ArrayList<String>          = arrayListOf(),
  @SerializedName("reference_prices"      ) var referencePrices     : ArrayList<ReferencePrices> = arrayListOf(),
  @SerializedName("purchase_discounts"    ) var purchaseDiscounts   : ArrayList<String>          = arrayListOf()

)