package com.altintasomer.application.accuweathercase.data.network

import com.altintas.application.accuweathercase.data.network.model.CurrentCondition
import com.altintas.application.accuweathercase.data.network.model.SearchList
import com.altintasomer.application.accuweathercase.data.network.model.WeatherCondition
import com.altintasomer.application.accuweathercase.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.*

interface AccuweatherApi {

    /*@Headers("Accept-Encoding", "gzip")*/
    @GET("locations/v1/cities/search")
    suspend fun search(@Query("apikey") apiKey : String? = API_KEY, @Query("q") searchKey : String?,@Query("offset") offset : String? = "5") : Response<List<SearchList>>

    /*@Headers("Accept-Encoding", "gzip")*/
    @GET("currentconditions/v1/{key}")
    suspend fun searchedWeatherCondition(@Path("key") key: String?, @Query("apikey") apiKey : String? = API_KEY) : Response<List<WeatherCondition>>

    /*@Headers("Accept-Encoding", "gzip")*/
    @GET("locations/v1/cities/geoposition/search")
    suspend fun geoLocationWeather(@Query("apikey") apiKey : String? = API_KEY, @Query("q") latLong : String?) : Response<SearchList>
}