package com.amirbahadoramiri.androidlearning.views.activities.mvvm.recyclerview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.views.activities.mvvm.MvvmUserTestClass;

import java.util.ArrayList;
import java.util.List;

public class MvvmRecyclerViewActivity extends BaseActivity {

    RecyclerView activity_mvvm_recyclerview_recyclerview;
    List<MvvmUserTestClass> userTestClassList = new ArrayList<>();
    MvvmAdapter mvvmAdapter = new MvvmAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_mvvm_recyclerview);
        setViewCompat();

        activity_mvvm_recyclerview_recyclerview = findViewById(R.id.activity_mvvm_recyclerview_recyclerview);
        userTestClassList.add(new MvvmUserTestClass("Amir","Amir@gmail.com"));
        userTestClassList.add(new MvvmUserTestClass("Ali","Ali@gmail.com"));
        userTestClassList.add(new MvvmUserTestClass("Reza","Reza@gmail.com"));
        userTestClassList.add(new MvvmUserTestClass("Mamad","Mamad@gmail.com"));
        userTestClassList.add(new MvvmUserTestClass("Hassan","Hassan@gmail.com"));
        mvvmAdapter.addData(userTestClassList);
        activity_mvvm_recyclerview_recyclerview.setAdapter(mvvmAdapter);
        activity_mvvm_recyclerview_recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }
}
