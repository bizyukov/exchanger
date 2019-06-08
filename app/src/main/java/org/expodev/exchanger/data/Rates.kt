package org.expodev.exchanger.data

import com.google.gson.annotations.SerializedName

data class Rates (
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Rate
)

data class Rate(
    @SerializedName("BGN") val bgn: Double,
    @SerializedName("NZD") val nzd: Double,
    @SerializedName("ILS") val ils: Double,
    @SerializedName("RUB") val rub: Double,
    @SerializedName("CAD") val cad: Double,
    @SerializedName("USD") val usd: Double,
    @SerializedName("PHP") val php: Double,
    @SerializedName("CHF") val chf: Double,
    @SerializedName("ZAR") val zar: Double,
    @SerializedName("AUD") val aud: Double,
    @SerializedName("JPY") val jpy: Double,
    @SerializedName("TRY") val _try: Double,
    @SerializedName("HKD") val hkd: Double,
    @SerializedName("MYR") val myr: Double,
    @SerializedName("THB") val thb: Double,
    @SerializedName("HRK") val hrk: Double,
    @SerializedName("NOK") val nok: Double,
    @SerializedName("IDR") val idr: Double,
    @SerializedName("DKK") val dkk: Double,
    @SerializedName("CZK") val czk: Double,
    @SerializedName("HUF") val huf: Double,
    @SerializedName("GBP") val gbp: Double,
    @SerializedName("MXN") val mxn: Double,
    @SerializedName("KRW") val krw: Double,
    @SerializedName("ISK") val isk: Double,
    @SerializedName("SGD") val sgd: Double,
    @SerializedName("BRL") val brl: Double,
    @SerializedName("PLN") val pln: Double,
    @SerializedName("INR") val inr: Double,
    @SerializedName("RON") val ron: Double,
    @SerializedName("CNY") val cny: Double,
    @SerializedName("SEK") val sek: Double
)
