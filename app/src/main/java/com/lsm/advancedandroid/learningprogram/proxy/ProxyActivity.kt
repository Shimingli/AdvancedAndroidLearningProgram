package com.lsm.advancedandroid.learningprogram.proxy

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lsm.advancedandroid.learningprogram.R
import com.lsm.advancedandroid.learningprogram.proxy.demo.api.EnjoyWeatherApi
import com.lsm.advancedandroid.learningprogram.proxy.demo.api.WeatherApi
import com.lsm.advancedandroid.learningprogram.proxy.demo.retrofit.EnjoyRetrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Method


class ProxyActivity : AppCompatActivity(), ProxyInterceptor {

    private val TAG = "ProxyActivity"
    private var callback:CallBack?=null
    private var weatherApi: WeatherApi? = null
    private var enjoyWeatherApi: EnjoyWeatherApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)


       // callBackFun()

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://restapi.amap.com")
            .build()

        weatherApi = retrofit.create(WeatherApi::class.java)

        val enjoyRetrofit: EnjoyRetrofit = EnjoyRetrofit.Builder().baseUrl("https://restapi.amap.com").build()
        enjoyWeatherApi = enjoyRetrofit.create(EnjoyWeatherApi::class.java)

    }

    operator fun get(view: View?) {
        val call =
            weatherApi!!.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b")
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    try {
                        val string = body!!.string()
                        Log.i(TAG, "onResponse get: $string")
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        body!!.close()
                    }
                }
            }

            override fun onFailure(
                call: Call<ResponseBody?>,
                t: Throwable
            ) {
            }
        })
    }

    fun post(view: View?) {
        val call =
            weatherApi!!.postWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b")
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            ) {
                val body = response.body()
                try {
                    val string = body!!.string()
                    Log.i(TAG, "onResponse post: $string")
                } catch (e: IOException) {
                    e.printStackTrace()
                } finally {
                    body!!.close()
                }
            }

            override fun onFailure(
                call: Call<ResponseBody?>,
                t: Throwable
            ) {
            }
        })
    }

    fun enjoyGet(view: View?) {
        val call =
            enjoyWeatherApi!!.getWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b")
        call.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                Log.i(
                    TAG,
                    "onResponse enjoy get: " + response.body!!.string()
                )
                response.close()
            }
        })
    }

    fun enjoyPost(view: View?) {
        val call =
            enjoyWeatherApi!!.postWeather("110101", "ae6c53e2186f33bbf240a12d80672d1b")
        call.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {}

            @Throws(IOException::class)
            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                Log.i(
                    TAG,
                    "onResponse enjoy post: " + response.body!!.string()
                )
                response.close()
            }
        })
    }



    private fun callBackFun() {
        Thread {
//            callBack.onCallback(1)
          val call=  object :CallBack {
                override fun onCallback(code: InCallBack) {

                }
            }
            Log.e("ProxyActivity", Thread.currentThread().name)
            callback =ProxyUtils.getUIProxy<CallBack>(call)
            this.callbackFun(this.callback!!)

        }.start()
    }

    private fun callbackFun(callBack: CallBack) {
        Log.e(TAG+"callBack", Thread.currentThread().name)
        callBack.onCallback(object :InCallBack{
            override fun onCallback(code: Int) {
                Log.e(TAG+"incallBack", Thread.currentThread().name)
            }
        })

    }

    public interface CallBack{
        fun onCallback(code: InCallBack)
    }
    public interface InCallBack{
        fun onCallback(code: Int)
    }

    override fun onIntercept(`object`: Any?, method: Method?, args: Array<out Any>?): Boolean {

        return true
    }


}
