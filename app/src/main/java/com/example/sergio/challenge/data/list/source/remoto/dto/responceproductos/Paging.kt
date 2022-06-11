package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Paging (

  @SerializedName("total"           ) var total          : Int? = null,
  @SerializedName("primary_results" ) var primaryResults : Int? = null,
  @SerializedName("offset"          ) var offset         : Int? = null,
  @SerializedName("limit"           ) var limit          : Int? = null

)