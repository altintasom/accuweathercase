package com.altintasomer.application.accuweathercase.data.network.model

import com.altintas.application.accuweathercase.data.network.model.Imperial
import com.altintas.application.accuweathercase.data.network.model.Metric
import com.google.gson.annotations.SerializedName


data class Elevation (

  @SerializedName("Metric"   ) var Metric   : Metric?   = Metric(),
  @SerializedName("Imperial" ) var Imperial : Imperial? = Imperial()

)