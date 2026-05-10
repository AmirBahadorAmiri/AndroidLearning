package com.amirbahadoramiri.androidlearning.views.navigationcompnenttest

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.amirbahadoramiri.androidlearning.R
import com.amirbahadoramiri.androidlearning.bases.BaseActivity

class NavigationCompnentTest : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeEnabled()
        setContentView(R.layout.activity_navigation_compnent_test)
        setViewCompat()
    }
}