package com.nier.viewmodelsample.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.nier.viewmodelsample.R

class LifeCycleActivity : AppCompatActivity() {
    private val TAG = "LifeCycleActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        Log.i(TAG, "LifeCycleActivity start and register observer >>>>>")
//        LifeCycleManager().addListener(this)
//        Log.i(TAG, "LifeCycleActivity finish register observer <<<<<")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "LifeCycleActivity start and register observer >>>>>")
        Thread {

            addListener(this)

        }.start()
        Log.i(TAG, "LifeCycleActivity finish register observer <<<<<")
    }


    fun addListener(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(LifeCycleListener(owner))
    }

    class LifeCycleListener : LifecycleObserver {
        private val TAG = "LifeCycleListener"

        private val owner: LifecycleOwner;

        constructor(owner: LifecycleOwner) {
            this.owner = owner
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onLifeCycleCreate() {
            Log.d(TAG, "onLifeCycleCreate , " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onLifeCycleStart() {
            Log.d(TAG, "onLifeCycleStart , " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onLifeCycleResume() {
            Log.d(TAG, "onLifeCycleResume >>>> , " + Thread.currentThread().name)
            owner.lifecycle.addObserver(OtherLifeCycleListener())
            Log.d(TAG, "onLifeCycleResume <<<<, " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onLifeCyclePause() {
            Log.d(TAG, "onLifeCyclePause, "+ Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onLifeCycleStop() {
            Log.d(TAG, "onLifeCycleStop, "+ Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onLifeCycleDestroy() {
            Log.d(TAG, "onLifeCycleDestroy, " + Thread.currentThread().name)
        }

//        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//        fun onLifeCycle() {
//            Log.d(TAG, "onLifeCycle")
//        }
    }

    class OtherLifeCycleListener : LifecycleObserver {
        private val TAG = "OtherLifeCycleListener"

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onLifeCycleCreate() {
            Log.w(TAG, "onLifeCycleCreate, " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onLifeCycleStart() {
            Log.w(TAG, "onLifeCycleStart, " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onLifeCycleResume() {
            Log.w(TAG, "onLifeCycleResume, " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onLifeCyclePause() {
            Log.w(TAG, "onLifeCyclePause, " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onLifeCycleStop() {
            Log.w(TAG, "onLifeCycleStop, " + Thread.currentThread().name)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onLifeCycleDestroy() {
            Log.w(TAG, "onLifeCycleDestroy, " + Thread.currentThread().name)
        }

//        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//        fun onLifeCycle() {
//            Log.w(TAG, "onLifeCycle")
//        }
    }

}
