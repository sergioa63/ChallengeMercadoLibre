package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Shipping (

  @SerializedName("free_shipping" ) var freeShipping : Boolean?          = null,
  @SerializedName("mode"          ) var mode         : String?           = null,
  @SerializedName("tags"          ) var tags         : ArrayList<String> = arrayListOf(),
  @SerializedName("logistic_type" ) var logisticType : String?           = null,
  @SerializedName("store_pick_up" ) var storePickUp  : Boolean?          = null

)