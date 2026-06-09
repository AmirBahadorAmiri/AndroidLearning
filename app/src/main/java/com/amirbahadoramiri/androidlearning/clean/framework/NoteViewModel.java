package com.amirbahadoramiri.androidlearning.clean.framework;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;
import com.amirbahadoramiri.androidlearning.clean.core.repo.NoteRepository;
import com.amirbahadoramiri.androidlearning.clean.core.usecase.AddNote;
import com.amirbahadoramiri.androidlearning.clean.core.usecase.GetAllNote;
import com.amirbahadoramiri.androidlearning.clean.core.usecase.GetNote;
import com.amirbahadoramiri.androidlearning.clean.core.usecase.RemoveNote;
import com.amirbahadoramiri.androidlearning.clean.framework.db.DatabaseServices;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NoteViewModel extends AndroidViewModel {

    public NoteRepository repository = new NoteRepository(new RoomNoteDataSource(DatabaseServices.getDatabaseServices(getApplication()).getDao()));

    public UseCases useCases = new UseCases(
            new AddNote(repository),
            new GetAllNote(repository),
            new GetNote(repository),
            new RemoveNote(repository)
    );

    public MutableLiveData<Boolean> saved = new MutableLiveData<>();

    public NoteViewModel(@NonNull Application application) {
        super(application);
    }

    public boolean saveNote(Note note) {
        try {
            useCases.addNote.add(note);
            saved.postValue(true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Observable<Boolean> saveNoteRx(Note note) {
        return Observable.fromCallable(() -> saveNote(note))
                .subscribeOn(Schedulers.io());
    }

}
