package com.altintas.application.accuweathercase.data.network.model

import com.google.gson.annotations.SerializedName


data class TimeZone (

  @SerializedName("Code"             ) var Code             : String?  = null,
  @SerializedName("Name"             ) var Name             : String?  = null,
  @SerializedName("GmtOffset"        ) var GmtOffset        : Double?     = null,
  @SerializedName("IsDaylightSaving" ) var IsDaylightSaving : Boolean? = null,
  @SerializedName("NextOffsetChange" ) var NextOffsetChange : String?  = null

)