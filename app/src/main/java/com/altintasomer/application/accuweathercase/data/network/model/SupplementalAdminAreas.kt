package com.altintas.application.accuweathercase.data.network.model

import com.google.gson.annotations.SerializedName


data class SupplementalAdminAreas (

  @SerializedName("Level"         ) var Level         : Int?    = null,
  @SerializedName("LocalizedName" ) var LocalizedName : String? = null,
  @SerializedName("EnglishName"   ) var EnglishName   : String? = null

)