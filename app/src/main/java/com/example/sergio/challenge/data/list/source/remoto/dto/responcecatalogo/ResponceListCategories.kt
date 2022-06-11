package com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo

import com.google.gson.annotations.SerializedName


data class ResponceListCategories (

  @SerializedName("id"                  ) var id                 : String?               = null,
  @SerializedName("name"                ) var name               : String?               = null,
  @SerializedName("country_id"          ) var countryId          : String?               = null,
  @SerializedName("sale_fees_mode"      ) var saleFeesMode       : String?               = null,
  @SerializedName("mercadopago_version" ) var mercadopagoVersion : Int?                  = null,
  @SerializedName("default_currency_id" ) var defaultCurrencyId  : String?               = null,
  @SerializedName("immediate_payment"   ) var immediatePayment   : String?               = null,
  @SerializedName("payment_method_ids"  ) var paymentMethodIds   : ArrayList<String>     = arrayListOf(),
  @SerializedName("settings"            ) var settings           : Settings?             = Settings(),
  @SerializedName("currencies"          ) var currencies         : ArrayList<Currencies> = arrayListOf(),
  @SerializedName("categories"          ) var categories         : ArrayList<Categories> = arrayListOf(),
  @SerializedName("channels"            ) var channels           : ArrayList<String>     = arrayListOf()

)