package com.lsm.advancedandroid.learningprogram


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lsm.advancedandroid.learningprogram.custom_view.CustomViewActivity
import com.lsm.advancedandroid.learningprogram.jetpack.JetPackActivity
import com.lsm.advancedandroid.learningprogram.proxy.ProxyActivity
import com.lsm.advancedandroid.learningprogram.thread.Model
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Model.getInstance().init(this);


        Model.getInstance().getGlobalThreadPool().execute( Runnable() {

            runOnUiThread(Runnable {

            })

        });





        mGotoCustomViewActivity.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    CustomViewActivity::class.java
                )
            )
        }

        mProxyViewActivity.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    ProxyActivity::class.java
                )
            )
        }
        mJetPackActivity.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    JetPackActivity::class.java
                )
            )
        }

    }


}
