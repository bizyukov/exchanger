package org.expodev.exchanger.data

object SearchRepoProvider {
    fun provideRepo(): SearchRepo {
        return SearchRepo(ExchangeRatesApi.create())
    }
}