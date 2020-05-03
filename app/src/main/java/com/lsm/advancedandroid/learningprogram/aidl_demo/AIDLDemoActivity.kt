package com.lsm.advancedandroid.learningprogram.aidl_demo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lsm.advancedandroid.learningprogram.R
import kotlinx.android.synthetic.main.activity_a_i_d_l_demo.*

/**
 * @NAME: AIDLDemoActivity
 * @Des:
 * @USER: shiming
 * @DATE: 2020/5/3 14:57
 * @PROJECT_NAME: AdvancedAndroidLearningProgram
 */
class AIDLDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_i_d_l_demo)
        startService(Intent(this, GPSService::class.java))
        mBtnTest.setOnClickListener {
            val managerData = DataManager.getInstance().managerData
            Toast.makeText(this, managerData, Toast.LENGTH_SHORT).toString()
        }

    }

}