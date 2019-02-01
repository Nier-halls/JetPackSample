package com.nier.viewmodelsample.livedata

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

/**
 * Created by fgd
 * Date 2019/1/2
 */
val sPublicLiveData = MutableLiveData<String>()
const val TAG = "LiveDataSample"

fun startRefresh() {
    val startTime = SystemClock.elapsedRealtime()
    val timer = Timer()
    timer.scheduleAtFixedRate(1000, 1000) {
        val newValue = (SystemClock.elapsedRealtime() - startTime)/1000
        sPublicLiveData.postValue(newValue.toString())
    }
}
