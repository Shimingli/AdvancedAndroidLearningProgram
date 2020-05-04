package com.lsm.advancedandroid.learningprogram.jetpack

import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * @editor :
 * @description :
 * @author : ShiMing
 * @created : 2020-04-28 08:38
 */
class JetPackViewModel(application: Application) : AndroidViewModel(application) {

    var phoneInfo:MutableLiveData<String>?=null
    val context=application
    /*
    注意这个方法是有返回值的，注意在xml中的应用  ，xml需要引用他的数字 请注意
     */
    fun getPhoneInfoDes():MutableLiveData<String>?{
        if (phoneInfo==null){
            phoneInfo= MutableLiveData()
            phoneInfo?.value=""
        }
        return phoneInfo
    }
    fun  appendNumber(num :String){
        phoneInfo?.value=phoneInfo?.value+num
    }
    fun clear(){
        phoneInfo?.value=""
    }
    fun callPhone(){
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:" + phoneInfo!!.value)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun backspaceNumber(){
        val length=phoneInfo?.value?.length
        if (length!!>0){
            phoneInfo?.value=phoneInfo?.value?.substring(0,phoneInfo?.value?.length!!-1)
        }
    }

}