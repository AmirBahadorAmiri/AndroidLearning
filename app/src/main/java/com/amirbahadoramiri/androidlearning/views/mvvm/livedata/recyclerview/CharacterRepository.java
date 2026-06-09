package com.amirbahadoramiri.androidlearning.views.mvvm.livedata.recyclerview;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amirbahadoramiri.androidlearning.models.Character;
import com.amirbahadoramiri.androidlearning.models.CharacterJacksonWrapper;
import com.amirbahadoramiri.androidlearning.tools.logger.Logger;
import com.amirbahadoramiri.androidlearning.views.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CharacterRepository extends ViewModel {
    private static CharacterRepository instance;
    private MutableLiveData<List<Character>> characterLiveData;
    private MutableLiveData<String> errorLiveData;

    public static CharacterRepository getInstance() {
        if (instance == null) {
            instance = new CharacterRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Character>> getCharacterList() {
        if (characterLiveData == null)
            characterLiveData = new MutableLiveData<>();

        if (characterLiveData.getValue() != null) {
            return characterLiveData;
        }

        loadCharacterList();

        return characterLiveData;
    }

    public void refreshCharacter() {
        if (characterLiveData == null)
            characterLiveData = new MutableLiveData<>();
        loadCharacterList();
    }

    private void loadCharacterList() {
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
                        characterLiveData.setValue(characterJacksonWrapper.getResults());
                        Logger.debug("data loaded successfully");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorLiveData.setValue(e.getMessage());
                    }
                });
    }

    public MutableLiveData<String> getError() {
        if (errorLiveData == null)
            errorLiveData = new MutableLiveData<>();
        return errorLiveData;
    }

}
