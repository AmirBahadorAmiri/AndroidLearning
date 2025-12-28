package com.amirbahadoramiri.androidlearning.views.activities.mvvm.livedata;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.bases.BaseActivity;
import com.amirbahadoramiri.androidlearning.databinding.ActivityMvvmLivedataBinding;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;

public class MvvmLiveDataActivity extends BaseActivity {

    ActivityMvvmLivedataBinding binding;
    MvvmLiveDataAdapter adapter;
    CharacterViewModel characterViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.logd("onCreate");
        edgeEnabled();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_livedata);
        setViewCompat();

        adapter = new MvvmLiveDataAdapter();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerview.setAdapter(adapter);

        characterViewModel = new ViewModelProvider(this).get(CharacterViewModel.class);
        characterViewModel.getCharacterList()
                .observe(this, characterList -> {
                    adapter.setData(characterList);
                    adapter.notifyDataSetChanged();
                });
        characterViewModel.getError()
                .observe(this, s -> {
                    Logger.logd("data failed to load");
                    Logger.logd(s);
                });


//        if (savedInstanceState != null) {
//            characters.addAll(savedInstanceState.getParcelableArrayList("characters"));
//        } else {
//            RetrofitClient.getRetrofitInterfaces()
//                    .listCharactersJackson("https://rickandmortyapi.com/api/character/")
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new SingleObserver<>() {
//                        @Override
//                        public void onSubscribe(@NonNull Disposable d) {
//                        }
//
//                        @Override
//                        public void onSuccess(@NonNull CharacterJacksonWrapper characterJacksonWrapper) {
//                            Logger.logd("data loaded successfully");
//                            characters.addAll(characterJacksonWrapper.getResults());
//                            adapter.notifyDataSetChanged();
//                        }
//
//                        @Override
//                        public void onError(@NonNull Throwable e) {
//                            Logger.logd("data failed to load");
//                            Logger.logd(e.getMessage());
//                        }
//                    });
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.logd("onStart");
    }

    @Override
    protected void onSaveInstanceState(@androidx.annotation.NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putParcelableArrayList("characters", (ArrayList<? extends Parcelable>) characters);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.logd("onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.logd("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.logd("onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.logd("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.logd("onResume");
    }
}
