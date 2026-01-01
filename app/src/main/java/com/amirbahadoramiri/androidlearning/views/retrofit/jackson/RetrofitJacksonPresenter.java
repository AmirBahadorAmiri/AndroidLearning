package com.amirbahadoramiri.androidlearning.views.retrofit.jackson;

import com.amirbahadoramiri.androidlearning.models.CharacterJacksonWrapper;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.amirbahadoramiri.androidlearning.views.retrofit.RetrofitClient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RetrofitJacksonPresenter implements RetrofitJacksonContract.Presenter {

    RetrofitJacksonContract.View view;

    @Override
    public void onAttach(RetrofitJacksonContract.View view) {
        this.view = view;
        loadItem();
    }

    @Override
    public void onDetach() {
        view = null;
    }

    @Override
    public void loadItem() {
        RetrofitClient.getRetrofitInterfaces()
                .listCharactersJackson("https://rickandmortyapi.com/api/character/")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull CharacterJacksonWrapper characterJacksonWrapper) {
                        Logger.logd("success");
                        view.showItems(characterJacksonWrapper.getResults());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Logger.logd("fail");
                        Logger.logd(e.getMessage());
                    }
                });
    }
}
