package com.altintasomer.application.accuweathercase.data.network

import com.altintas.application.accuweathercase.data.network.model.SearchList
import com.altintasomer.application.accuweathercase.data.network.model.WeatherCondition
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api: AccuweatherApi
) : IWeatherRepository {

    override suspend fun search(searchKey: String?) : Response<List<SearchList>> {
        return api.search(searchKey = searchKey)
    }

    override suspend fun searchedWeatherCondition(key: String?) : Response<List<WeatherCondition>>{
       return api.searchedWeatherCondition(key = key)
    }

    override suspend fun geoLocationWeather(latLong: String?): Response<SearchList> {
        return api.geoLocationWeather(latLong = latLong)
    }
}