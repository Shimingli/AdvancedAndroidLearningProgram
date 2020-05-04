package com.lsm.advancedandroid.learningprogram.image_compression

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lsm.advancedandroid.learningprogram.R
import kotlinx.android.synthetic.main.activity_image_compression.*
import java.io.ByteArrayOutputStream

class ImageCompressionActivity : AppCompatActivity() {

    @SuppressLint("WrongThread")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_compression)

//        val option=BitmapFactory.Options()
//        //采样率
//        option.inSampleSize=1
//        val bitmap=BitmapFactory.decodeResource(resources,R.mipmap.demo,option)
//        mImageView.setImageBitmap(bitmap)


        val bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.demo)
        val bitmap2 = Bitmap.createScaledBitmap(bitmap1,bitmap1.width/2,bitmap1.height/2,true)
        mImageView.setImageBitmap(bitmap2)

        //最好把这个图片copy到本地区，这样就能够看到真正质量的却别
        val byteArrayOutputStream = ByteArrayOutputStream()

        val compress = bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)


    }
}
