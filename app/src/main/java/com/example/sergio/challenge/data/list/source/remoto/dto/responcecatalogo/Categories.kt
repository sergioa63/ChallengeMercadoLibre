package com.example.sergio.challenge.data.list.source.remoto.dto.responcecatalogo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Categories (
  @PrimaryKey @SerializedName("id"   ) var id   : String = "0",
  @SerializedName("name" ) var name : String? = null
): Parcelable