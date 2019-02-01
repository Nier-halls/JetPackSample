package com.nier.viewmodelsample.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.nier.viewmodelsample.R

class LiveDataSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_sample)

        startRefresh()

        val tvResult = findViewById<TextView>(R.id.tv_result)
        tvResult.setOnClickListener { v ->
            Log.d(TAG, this.toString())
            startActivity(Intent(this, SampleActivity1::class.java))
        }

        val observer: Observer<String> = Observer { result ->
            Log.d(TAG, "### Main refresh data $result")
            tvResult.text = result
        }
        sPublicLiveData.observe(this, observer)

//        tvResult.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                Log.d(TAG, this.toString())
//                Log.d(TAG, this@LiveDataSampleActivity.toString())
//                this@LiveDataSampleActivity.startActivity(Intent(this@LiveDataSampleActivity, SampleActivity1::class.java))
//            }
//        })
    }
}
