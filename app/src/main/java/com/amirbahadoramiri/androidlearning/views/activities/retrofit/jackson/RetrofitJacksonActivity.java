package com.amirbahadoramiri.androidlearning.views.activities.retrofit.jackson;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.models.Character;

import java.util.ArrayList;
import java.util.List;

public class RetrofitJacksonActivity extends BaseActivity implements RetrofitJacksonContract.View {

    RetrofitJacksonPresenter presenter;
    RecyclerView recyclerview;
    List<Character> characterList = new ArrayList<>();
    RetrofitJacksonAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_retrofit_gson);
        setViewCompat();
        adapter = new RetrofitJacksonAdapter(characterList);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        presenter = new RetrofitJacksonPresenter();
        presenter.onAttach(this);
    }

    @Override
    public void showItems(List<Character> characters) {
        if (characterList == null) characterList = new ArrayList<>();
        if (!characterList.isEmpty()) characterList.clear();
        characterList.addAll(characters);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
