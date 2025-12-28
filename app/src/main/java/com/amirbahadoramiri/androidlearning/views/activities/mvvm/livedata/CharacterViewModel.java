package com.amirbahadoramiri.androidlearning.views.activities.mvvm.livedata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.amirbahadoramiri.androidlearning.models.Character;

import java.util.List;

public class CharacterViewModel extends AndroidViewModel {
    private CharacterRepository repository;
    private MutableLiveData<List<Character>> characterLiveData;

    public CharacterViewModel(@NonNull Application application) {
        super(application);
        repository = CharacterRepository.getInstance();
        characterLiveData = repository.getCharacterList();
    }

    public MutableLiveData<List<Character>> getCharacterList() {
        return characterLiveData;
    }

    public void refresh() {
        repository.refreshProducts();
    }

    public MutableLiveData<String> getError() {
        return repository.getError();
    }

}
