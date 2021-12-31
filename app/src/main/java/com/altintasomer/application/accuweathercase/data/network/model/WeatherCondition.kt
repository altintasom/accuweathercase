package com.altintasomer.application.accuweathercase.data.network.model

import android.os.Parcelable
import com.altintas.application.accuweathercase.data.network.model.Temperature
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherCondition (

    @SerializedName("LocalObservationDateTime" ) var LocalObservationDateTime : String?      = null,
    @SerializedName("EpochTime"                ) var EpochTime                : Int?         = null,
    @SerializedName("WeatherText"              ) var WeatherText              : String?      = null,
    @SerializedName("WeatherIcon"              ) var WeatherIcon              : Int?         = null,
    @SerializedName("HasPrecipitation"         ) var HasPrecipitation         : Boolean?     = null,
    @SerializedName("PrecipitationType"        ) var PrecipitationType        : String?      = null,
    @SerializedName("IsDayTime"                ) var IsDayTime                : Boolean?     = null,
    @SerializedName("Temperature"              ) var Temperature              : Temperature? = Temperature(),
    @SerializedName("MobileLink"               ) var MobileLink               : String?      = null,
    @SerializedName("Link"                     ) var Link                     : String?      = null

): Parcelable
