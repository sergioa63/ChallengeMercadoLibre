package com.example.sergio.challenge.data.list.source.remoto.dto.responceproductos

import com.google.gson.annotations.SerializedName


data class Attributes (

  @SerializedName("name"                 ) var name               : String?           = null,
  @SerializedName("value_struct"         ) var valueStruct        : String?           = null,
  @SerializedName("values"               ) var values             : ArrayList<Values> = arrayListOf(),
  @SerializedName("attribute_group_id"   ) var attributeGroupId   : String?           = null,
  @SerializedName("source"               ) var source             : Int?              = null,
  @SerializedName("id"                   ) var id                 : String?           = null,
  @SerializedName("value_id"             ) var valueId            : String?           = null,
  @SerializedName("value_name"           ) var valueName          : String?           = null,
  @SerializedName("attribute_group_name" ) var attributeGroupName : String?           = null

)