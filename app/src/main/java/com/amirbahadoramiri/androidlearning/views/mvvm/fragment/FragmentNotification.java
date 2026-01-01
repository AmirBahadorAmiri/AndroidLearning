package com.amirbahadoramiri.androidlearning.views.mvvm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.amirbahadoramiri.androidlearning.R;
import com.amirbahadoramiri.androidlearning.databinding.FragmentNotificationBinding;
import com.amirbahadoramiri.androidlearning.models.CharacterJacksonWrapper;
import com.amirbahadoramiri.androidlearning.views.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FragmentNotification extends Fragment {

    private static FragmentNotification instance;
    CharacterNotificationFragmentAdapter adapter;
    FragmentNotificationBinding binding;

    public static FragmentNotification getInstance() {
        if (instance == null) {
            instance = new FragmentNotification();
            instance.setArguments(new Bundle());
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new CharacterNotificationFragmentAdapter();
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.setAdapter(adapter);

        RetrofitClient.getRetrofitInterfaces()
                .listCharactersJackson("https://rickandmortyapi.com/api/character/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull CharacterJacksonWrapper characterJacksonWrapper) {
                        adapter.setData(characterJacksonWrapper.getResults());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });

    }
}