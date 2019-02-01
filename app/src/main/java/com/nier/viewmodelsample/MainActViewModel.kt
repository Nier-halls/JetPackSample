package com.nier.viewmodelsample

import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * Created by fgd
 * Date 2018/12/28
 */
class MainActViewModel : ViewModel() {
    val TAG = "ViewModelSamle"

    private var pubData: String? = "123"

    fun getData(): String? {
        return pubData
    }

    fun setData(value: String?) {
        this.pubData = value
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "MainActViewModel >>> onCleared")
    }

}