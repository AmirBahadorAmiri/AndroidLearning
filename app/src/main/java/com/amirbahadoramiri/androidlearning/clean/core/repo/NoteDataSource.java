package com.amirbahadoramiri.androidlearning.clean.core.repo;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;

import java.util.List;

public interface NoteDataSource {

    void add(Note note);
    Note get(Long id);
    List<Note> get_all();
    void remove(Note note);

}
