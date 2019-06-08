package org.expodev.exchanger.data

class SearchRepo (  val apiService: ExchangeRatesApi) {

    fun getRates(): io.reactivex.Observable<Rates>{
        return apiService.getRates()
    }
}