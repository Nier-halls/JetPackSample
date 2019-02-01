package com.nier.viewmodelsample

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

const val TAG = "ViewModelSamle"

class MainActivity : AppCompatActivity() {

    lateinit var mEditText: EditText;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditText = findViewById(R.id.user_edit)

        val model = ViewModelProviders.of(this).get(MainActViewModel::class.java)
        Log.d(TAG, "model instance => " + System.identityHashCode(model).toString())
        Log.d(TAG, model.getData())
        model.setData("heihei")
        Log.d(TAG, model.getData())
        Log.d(TAG, "lastCustomNonConfigurationInstance  RAW >>> " + (lastCustomNonConfigurationInstance is HashMap<*, *>))

        Log.d(TAG, "lastCustomNonConfigurationInstance >>> " + (lastCustomNonConfigurationInstance as? HashMap<*, *>)?.get("MyData"))
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        Log.d(TAG, "MainActivity >>> onRetainCustomNonConfigurationInstance")
        val map = mapOf<String, String>(Pair("MyData", mEditText.text.toString()))
        return map
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "MainActivity >>> onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "MainActivity >>> onDestroy")
    }

}
