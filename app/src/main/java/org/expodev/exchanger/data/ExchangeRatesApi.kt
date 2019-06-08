package org.expodev.exchanger.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ExchangeRatesApi {

    @GET("/")
    fun getRates(): io.reactivex.Observable<Rates>

    companion object Factory{
        fun create(): ExchangeRatesApi{
            val gson: Gson = GsonBuilder().setLenient().create()
            val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://exchangeratesapi.expodev.org")
            //My provider is blocking exchangeratesapi.io, so i did create small proxy script for it
                .build()
            return retrofit.create(ExchangeRatesApi::class.java)
        }
    }
}