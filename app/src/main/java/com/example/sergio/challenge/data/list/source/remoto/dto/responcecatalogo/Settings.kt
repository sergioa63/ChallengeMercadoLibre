package com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo

import com.google.gson.annotations.SerializedName


data class Settings (

  @SerializedName("identification_types"       ) var identificationTypes      : ArrayList<String> = arrayListOf(),
  @SerializedName("taxpayer_types"             ) var taxpayerTypes            : ArrayList<String> = arrayListOf(),
  @SerializedName("identification_types_rules" ) var identificationTypesRules : String?           = null

)