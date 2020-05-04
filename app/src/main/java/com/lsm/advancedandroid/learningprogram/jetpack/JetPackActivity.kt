package com.lsm.advancedandroid.learningprogram.jetpack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lsm.advancedandroid.learningprogram.R
import com.lsm.advancedandroid.learningprogram.databinding.ActivityJetPackBinding


/**
 * @editor :
 * @description :
 * @author : ShiMing
 * @created : 2020-04-28 08:38
 */
class JetPackActivity : AppCompatActivity() {
    var binding: ActivityJetPackBinding? = null

    var model: JetPackViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //记得这个是自动生成的  ActivityJetPackBinding
        binding =
            DataBindingUtil.setContentView<ActivityJetPackBinding>(this, R.layout.activity_jet_pack)
        //新版用法
        model =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                JetPackViewModel::class.java
            )
        //建立绑定
        binding?.vm = model
        //让感应生效
        binding?.lifecycleOwner = this
    }
}
