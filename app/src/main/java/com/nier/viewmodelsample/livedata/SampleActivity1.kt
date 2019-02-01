package com.nier.viewmodelsample.livedata

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import com.nier.viewmodelsample.R

class SampleActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample1)

        val tvResult = findViewById<TextView>(R.id.tv_result)
        tvResult.setOnClickListener { v ->
            Log.d(TAG, this.toString())
            startActivity(Intent(this, SampleActivity2::class.java))
        }

        val observer: Observer<String> = Observer { result ->
            Log.d(TAG, "### Activity111 refresh data $result")
            tvResult.text = result
        }
        sPublicLiveData.observe(this, observer)
    }
}
