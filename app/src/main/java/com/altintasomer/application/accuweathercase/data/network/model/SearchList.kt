package com.altintas.application.accuweathercase.data.network.model

import com.altintasomer.application.accuweathercase.data.network.model.AdministrativeArea
import com.altintasomer.application.accuweathercase.data.network.model.Country
import com.altintasomer.application.accuweathercase.data.network.model.GeoPosition
import com.google.gson.annotations.SerializedName


data class SearchList (

  @SerializedName("Version"                ) var Version                : Int?                              = null,
  @SerializedName("Key"                    ) var Key                    : String?                           = null,
  @SerializedName("Type"                   ) var Type                   : String?                           = null,
  @SerializedName("Rank"                   ) var Rank                   : Int?                              = null,
  @SerializedName("LocalizedName"          ) var LocalizedName          : String?                           = null,
  @SerializedName("EnglishName"            ) var EnglishName            : String?                           = null,
  @SerializedName("PrimaryPostalCode"      ) var PrimaryPostalCode      : String?                           = null,
  @SerializedName("Region"                 ) var Region                 : Region?                           = Region(),
  @SerializedName("Country"                ) var Country                : Country?                          = Country(),
  @SerializedName("AdministrativeArea"     ) var AdministrativeArea     : AdministrativeArea?               = AdministrativeArea(),
  @SerializedName("TimeZone"               ) var TimeZone               : TimeZone?                         = TimeZone(),
  @SerializedName("GeoPosition"            ) var GeoPosition            : GeoPosition?                      = GeoPosition(),
  @SerializedName("IsAlias"                ) var IsAlias                : Boolean?                          = null,
  @SerializedName("SupplementalAdminAreas" ) var SupplementalAdminAreas : ArrayList<SupplementalAdminAreas> = arrayListOf(),
  @SerializedName("DataSets"               ) var DataSets               : ArrayList<String>                 = arrayListOf()

)