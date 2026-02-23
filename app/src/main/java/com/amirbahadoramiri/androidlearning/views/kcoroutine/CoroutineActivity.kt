package com.amirbahadoramiri.androidlearning.views.kcoroutine

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.amirbahadoramiri.androidlearning.R
import com.amirbahadoramiri.androidlearning.bases.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutineActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeEnabled()
        setContentView(R.layout.activity_coroutine)
        setViewCompat()

        findViewById<Button>(R.id.button).setOnClickListener {
            lifecycleScope.launch {
                setText("Hello World")
            }
        }

    }

    suspend fun setText(str: String) {
        delay(3000)
        findViewById<TextView>(R.id.textView3).text = str
    }

}
