package com.amirbahadoramiri.androidlearning.views.ion;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.models.Character;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.List;

public class IONActivity extends BaseActivity {

    RecyclerView recyclerview;
    List<com.amirbahadoramiri.androidlearning.models.Character> characters = new ArrayList<>();
    IONAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_ion);
        setViewCompat();

        adapter = new IONAdapter(characters);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);

        Ion.with(this)
                .load("https://rickandmortyapi.com/api/character/")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject object) {
                        if (e != null) {
                            Logger.loge(e.getMessage());
                        } else {
                            JsonArray results = object.get("results").getAsJsonArray();
                            for (int i = 0; i < results.size(); i++) {
                                JsonObject chararc = results.get(i).getAsJsonObject();
                                String name = (i+1)+". "+chararc.get("name").getAsString();
                                String image = chararc.get("image").getAsString();
                                characters.add(new Character(name, image));
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }
}
