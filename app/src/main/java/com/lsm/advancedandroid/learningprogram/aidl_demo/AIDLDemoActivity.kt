package com.lsm.advancedandroid.learningprogram.aidl_demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lsm.advancedandroid.learningprogram.R
import kotlinx.android.synthetic.main.activity_a_i_d_l_demo.*

/**
 * @NAME: AIDLDemoActivity
 * @Des:  广播太重，溯源太慢了，socket  内容提供者 binder  下面的代码是不是太麻烦了 现在开始封装
 * @USER: shiming
 * @DATE: 2020/5/3 14:57
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
class AIDLDemoActivity : AppCompatActivity() {

    private var connection: ServiceConnection? = null

    private var asInterface: IMyAidlInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_i_d_l_demo)
        val intent=Intent(this, GPSService::class.java)
        startService(intent)
         connection = object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                 asInterface = IMyAidlInterface.Stub.asInterface(service)
            }

        }

        bindService(intent, connection, Context.BIND_AUTO_CREATE)


        mBtnTest.setOnClickListener {
            //val managerData = DataManager.getInstance().managerData
            //这样就可以获取到跨进程的的数据
            Toast.makeText(this, "当前信息：" + asInterface?.msg, Toast.LENGTH_SHORT).show()

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

}