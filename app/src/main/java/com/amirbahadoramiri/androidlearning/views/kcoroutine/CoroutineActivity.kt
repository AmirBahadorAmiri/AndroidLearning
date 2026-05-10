package com.amirbahadoramiri.androidlearning.views.kcoroutine

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.amirbahadoramiri.androidlearning.R
import com.amirbahadoramiri.androidlearning.bases.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeEnabled()
        setContentView(R.layout.activity_coroutine)
        setViewCompat()

        findViewById<Button>(R.id.button).setOnClickListener {
//            CoroutineScope(Dispatchers.Default).launch {
//                setText("Hello World")
//            }

//            GlobalScope.launch {
//                setText("Hello World")
//            }

            CoroutineScope(Dispatchers.IO).launch {
                setText("Hello World")
            }

        }

    }

    suspend fun setText(str: String) {
        delay(3000)
//        withContext(Dispatchers.Main){
//            findViewById<TextView>(R.id.textView3).text = str
//        }

//        MainScope().launch {
//            findViewById<TextView>(R.id.textView3).text = str
//        }

        lifecycleScope.launch {
            findViewById<TextView>(R.id.textView3).text = str
        }

    }

}
