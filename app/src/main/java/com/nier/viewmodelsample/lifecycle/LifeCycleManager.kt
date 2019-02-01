package com.nier.viewmodelsample.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * Created by fgd
 * Date 2019/1/4
 */
val TAG = "LifeCycleManager"

class LifeCycleManager() {

    class LifeCycleListener : LifecycleObserver {

//        var timer: Timer = Timer()
//        var args = 1
//        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//        fun onStartTodoSomething() {
//            this.timer.scheduleAtFixedRate(1000, 1000) {
//                Log.d(TAG, "onStart doing " + args++)
//            }
//        }
//
//        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//        fun onStopTodoSomething() {
//            this.timer.cancel()
//        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onLifeCycleCreate() {
            Log.d(TAG, "onLifeCycleCreate")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onLifeCycleStart() {
            Log.d(TAG, "onLifeCycleStart")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onLifeCycleResume() {
            Log.d(TAG, "onLifeCycleResume")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onLifeCyclePause() {
            Log.d(TAG, "onLifeCyclePause")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onLifeCycleStop() {
            Log.d(TAG, "onLifeCycleStop")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onLifeCycleDestroy() {
            Log.d(TAG, "onLifeCycleDestroy")
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        fun onLifeCycle() {
            Log.d(TAG, "onLifeCycle")
        }
    }


    fun addListener(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(LifeCycleListener())
    }

}