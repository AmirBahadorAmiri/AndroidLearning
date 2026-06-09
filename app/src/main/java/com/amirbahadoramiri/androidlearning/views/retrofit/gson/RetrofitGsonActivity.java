package com.amirbahadoramiri.androidlearning.views.retrofit.gson;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.models.Character;
import com.amirbahadoramiri.androidlearning.models.CharacterGsonWrapper;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.amirbahadoramiri.androidlearning.views.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class RetrofitGsonActivity extends BaseActivity {

    RecyclerView recyclerview;
    RetrofitGsonMvvmAdapter retrofitGsonMvvmAdapter;
    List<Character> characterList = new ArrayList<>();

    @Inject
    RetrofitClient.RetrofitInterfaces getRetrofitInterfaces;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        edgeEnabled();
        setContentView(R.layout.activity_retrofit_gson);
        setViewCompat();

        retrofitGsonMvvmAdapter = new RetrofitGsonMvvmAdapter(characterList);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(retrofitGsonMvvmAdapter);

        getRetrofitInterfaces
                .listCharacters("https://rickandmortyapi.com/api/character/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CharacterGsonWrapper>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull CharacterGsonWrapper characterGsonWrapper) {
                        Logger.debug("is successfull");
                        Logger.debug("count: " + characterGsonWrapper.getResults().size());
                        characterList.addAll(characterGsonWrapper.getResults());
                        retrofitGsonMvvmAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.debug("is not successfull");
                        Logger.debug(e.getMessage());
                    }
                });

    }
}
