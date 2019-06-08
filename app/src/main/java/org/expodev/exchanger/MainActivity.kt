package org.expodev.exchanger

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.EditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.expodev.exchanger.data.Rate
import org.expodev.exchanger.data.SearchRepo
import org.expodev.exchanger.data.SearchRepoProvider
import java.util.concurrent.TimeUnit

const val TAG: String = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val repository: SearchRepo = SearchRepoProvider.provideRepo()
    lateinit var plnRate: EditText
    lateinit var usdRate: EditText
    private var isFirst: Boolean = true
    private val times:Long = 10000
    private val delay:Long = 60000
    private var count:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plnRate = findViewById<EditText>(R.id.plnRate)
        usdRate = findViewById<EditText>(R.id.usdRate)

        startRequest()
    }

    private fun startRequest() {
        runRequest(1, 1)
    }

    private fun runRequest(times: Long, delay: Long){
        compositeDisposable.add(
            repository.getRates()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .delay(delay, TimeUnit.MILLISECONDS)
                .repeat(times)
                .subscribe({
                        result ->
                    runOnUiThread {
                        count++
                        updateFields(result.rates)
                    }
                },{
                        throwable ->
                    runOnUiThread {
                        showErrorMsg(throwable.message)
                    }
                })
        )
    }

    private fun showErrorMsg(errMsg: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(checkMsg(errMsg))

        builder.setPositiveButton(android.R.string.ok) { _, _ ->
            startRequest()
        }
        builder.show()
    }

    private fun checkMsg(errMsg: String?) =
        if (errMsg.isNullOrEmpty()) "Something is wrong with request" else errMsg

    private fun updateFields(rates: Rate){
        if(isFirst){
            isFirst = false
            runRequest(times, delay)
        }
        plnRate.setText(rates.pln.toString())
        usdRate.setText(rates.usd.toString())
    }
}
