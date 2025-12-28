package com.amirbahadoramiri.androidlearning.views.activities.mvvm.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amirbahadoramiri.androidlearning.models.Character;
import com.amirbahadoramiri.androidlearning.models.CharacterJacksonWrapper;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.amirbahadoramiri.androidlearning.views.activities.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CharacterRepository extends ViewModel {
    private static CharacterRepository instance;
    private MutableLiveData<List<Character>> mutableLiveData;
    private MutableLiveData<String> errorLiveData;

    public static CharacterRepository getInstance() {
        if (instance == null) {
            instance = new CharacterRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Character>> getCharacterList() {
        if (mutableLiveData == null)
            mutableLiveData = new MutableLiveData<>();

        if (mutableLiveData.getValue() != null) {
            return mutableLiveData;
        }

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
                        mutableLiveData.setValue(characterJacksonWrapper.getResults());
                        Logger.logd("data loaded successfully");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorLiveData.setValue(e.getMessage());
                    }
                });

        return mutableLiveData;
    }

    public void refreshProducts() {
        getCharacterList(); // دوباره درخواست می‌فرسته و کش رو آپدیت می‌کنه
    }

    public MutableLiveData<String> getError() {
        if (errorLiveData == null)
            errorLiveData = new MutableLiveData<>();
        return errorLiveData;
    }

}
