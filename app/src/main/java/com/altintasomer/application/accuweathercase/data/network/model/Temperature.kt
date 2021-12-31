package com.altintas.application.accuweathercase.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Temperature (

  @SerializedName("Metric"   ) var Metric   : Metric?   = Metric(),
  @SerializedName("Imperial" ) var Imperial : Imperial? = Imperial()

): Parcelable