package com.amirbahadoramiri.androidlearning.views.kotlin.mvc.end_of_recyclerview

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amirbahadoramiri.androidlearning.R
import com.amirbahadoramiri.androidlearning.bases.BaseActivity
import com.amirbahadoramiri.androidlearning.models.User

class EndOfRecyclerView : BaseActivity() {

    lateinit var recyclerview: RecyclerView
    val adapter = EndOfRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        edgeEnabled()
        setContentView(R.layout.activity_endofrecyclerview)
        setViewCompat()

        var users = arrayListOf<User>()

        for (item in 0..100) {
            users.add(User("Amir", "Amiri $item"))
        }

        adapter.setData(users)

        recyclerview = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

    }

}