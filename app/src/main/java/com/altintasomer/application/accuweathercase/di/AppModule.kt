package com.altintasomer.application.accuweathercase.di

import com.altintasomer.application.accuweathercase.data.network.AccuweatherApi
import com.altintasomer.application.accuweathercase.data.network.WeatherRepository
import com.altintasomer.application.accuweathercase.utils.Constants.Companion.BASE_URL
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor :HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }




    @Singleton
    @Provides
    fun provideRetrofitBuilder(client :OkHttpClient) : AccuweatherApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AccuweatherApi::class.java)

    @Singleton
    @Provides
    fun provideWeatherRepo(api : AccuweatherApi) : WeatherRepository{
        return WeatherRepository(api = api)
    }
}