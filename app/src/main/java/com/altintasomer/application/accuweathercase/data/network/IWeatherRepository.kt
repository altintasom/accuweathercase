package com.altintasomer.application.accuweathercase.data.network

import com.altintas.application.accuweathercase.data.network.model.CurrentCondition
import com.altintas.application.accuweathercase.data.network.model.SearchList
import com.altintasomer.application.accuweathercase.data.network.model.WeatherCondition
import retrofit2.Response


interface IWeatherRepository {

    suspend fun search(searchKey : String?) : Response<List<SearchList>>

    suspend fun searchedWeatherCondition(key:String?) : Response<List<WeatherCondition>>

    suspend fun geoLocationWeather(latLong: String?) : Response<SearchList>
}