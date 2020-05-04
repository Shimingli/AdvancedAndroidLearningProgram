package com.lsm.advancedandroid.learningprogram.proxy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.lsm.advancedandroid.learningprogram.R
import java.lang.reflect.Method


class ProxyActivity : AppCompatActivity(), ProxyInterceptor {

    private val TAG = "ProxyActivity"
    private var callback:CallBack?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)


        callBackFun()


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
