package com.altintasomer.application.accuweathercase.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altintas.application.accuweathercase.data.network.model.SearchList
import com.altintasomer.application.accuweathercase.data.network.WeatherRepository
import com.altintasomer.application.accuweathercase.data.network.model.WeatherCondition
import com.altintasomer.application.accuweathercase.data.network.model.local.SearchingItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "ListViewModel"

@HiltViewModel
class GeneralViewModelModel @Inject constructor(
    private val repo: WeatherRepository
) : ViewModel() {
    /**
     * Burada finish-loading_error nesneleri ile api işlemlerinin durumunu handle edip live data ile ilgili ui ı kontrol ediyoruz
     *
     * */
    private val _finish = MutableLiveData<Boolean>()
    val finish: MutableLiveData<Boolean> get() = _finish

    private val _error = MutableLiveData<Boolean>()
    val error: MutableLiveData<Boolean> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean> get() = _loading

    private val _searchItemList = MutableLiveData<ArrayList<SearchingItem>>()
    val searchItemList : LiveData<ArrayList<SearchingItem>> get() = _searchItemList
    private val _rKey = MutableLiveData<String>()
    val rKey : LiveData<String>  get() = _rKey
    private var _locationName:String = "istanbul"
    val locationName:String get() = _locationName

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery:LiveData<String> get() = _searchQuery

    fun setSearchQuery(searchKey: String){
        _searchQuery.postValue(searchKey)
    }

    private val _weatherCondition = MutableLiveData<WeatherCondition>()
    val weatherCondition: MutableLiveData<WeatherCondition> get() = _weatherCondition

    /**
     * SearchView ile arama yapılırken api den bu fonsiyon ile aranılan bölgenin bilgileri alınıyor.
     * */
    fun search(searchKey: String?) {

        if ((searchKey?.length ?: 0 ) > 3)
            _loading.postValue(true)
            viewModelScope.launch(Dispatchers.IO) {
                val response = repo.search(searchKey = searchKey)

                if (response.isSuccessful) {
                    _searchItemList.postValue(convertSearchListToSearchingItemList(response.body()))

                } else {
                    Log.d(TAG, "search: null")
                    _loading.postValue(false)
                    _error.postValue(true)
                }
            }
    }

    /**
     * Api den dönen nesneyi api local bir objeye dönüştürüyoruz.
     * */
    private fun convertSearchListToSearchingItemList(searchLists: List<SearchList>?): ArrayList<SearchingItem>{
        val searchingItemList = ArrayList<SearchingItem>()
        searchLists?.forEach {
            val searchingItem = SearchingItem(name = it.LocalizedName+" - "+it.Country?.LocalizedName, key = it.Key, latLong = (it.GeoPosition?.Latitude.toString()+","+it.GeoPosition?.Longitude.toString()))
            searchingItemList.add(searchingItem)
        }
        return searchingItemList
    }

    /**
     * Konuma göre api den arama yapılıyor
     * */
    fun searchGeoPosition(latLong: String){
        viewModelScope.launch(Dispatchers.IO){
            _loading.postValue(true)
            val response = repo.geoLocationWeather(latLong)

            if (response.isSuccessful) {
                _rKey.postValue(response.body()?.Key?:"0")
                _locationName = response.body()?.LocalizedName ?:"Istanbul"
                _error.postValue(false)
            } else {
                Log.d(TAG, "search: null")
                _loading.postValue(false)
                _error.postValue(true)
            }

        }
    }

    /**
     * burada Kelime ile aramana veya konuma göre aramada çıkan bölgenin key ini alıp hava durmunun ayrıntısını alıyoruz
     * */
    fun getWeatherCondition(key : String){
        viewModelScope.launch(Dispatchers.IO){
            val response = repo.searchedWeatherCondition(key)

            if (response.isSuccessful){
                Log.d(TAG, "getWeatherCondition: "+response.body())
                _weatherCondition.postValue( response.body()?.get(0))
                _loading.postValue(false)
                _finish.postValue(true)
                _error.postValue(false)
            }else{
                Log.e(TAG, "getWeatherCondition: error" )
                _loading.postValue(false)
                _finish.postValue(true)
                _error.postValue(true)
            }
        }
    }

}