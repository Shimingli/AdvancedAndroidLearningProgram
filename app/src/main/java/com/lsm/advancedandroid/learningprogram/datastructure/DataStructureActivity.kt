package com.lsm.advancedandroid.learningprogram.datastructure

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lsm.advancedandroid.learningprogram.R
import kotlinx.android.synthetic.main.activity_data_structure.*
import java.util.*

class DataStructureActivity : AppCompatActivity(), View.OnClickListener {
    var hashMap: HashMap<Int, String> = HashMap<Int, String>()
    var sparseArray: SparseArray<String> = SparseArray<String>()
    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_structure)
       val arr={1}

        testHaspMap.setOnClickListener(this)
        testSparseArray.setOnClickListener(this)

    }
    @SuppressLint("StaticFieldLeak")
    override fun onClick(v: View?) {
       when(v?.id){
           R.id.testSparseArray->{
               object : AsyncTask<Void?, Void?, Void?>() {
                   override fun doInBackground(vararg params: Void?): Void? {
                       for (i in 0..99) {
                           sparseArray.put(i,"Demo")
                           println("shiming testSparseArray  i-=="+i)
                       }
                       return null
                   }
               }.execute()
           }
           R.id.testHaspMap->{
               object : AsyncTask<Void?, Void?, Void?>() {

                   override fun doInBackground(vararg params: Void?): Void? {
                       for (i in 0..99) {
                           hashMap[i]="Demo"
                           println("shiming  testHaspMap  i-=="+i)
                   }
                       return null
                   }
               }.execute()
           }

       }
    }
}
