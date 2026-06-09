package com.amirbahadoramiri.androidlearning.clean.core.repo;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;

import java.util.List;

public class NoteRepository {

    private NoteDataSource noteDataSource;

    public NoteRepository(NoteDataSource noteDataSource) {
        this.noteDataSource = noteDataSource;
    }

    public void add(Note note) {
        noteDataSource.add(note);
    }

    public Note get(Long id) {
        return noteDataSource.get(id);
    }

    public List<Note> get_all() {
        return noteDataSource.get_all();
    }

    public void remove(Note note) {
        noteDataSource.remove(note);
    }

}
