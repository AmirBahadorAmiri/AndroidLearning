package com.amirbahadoramiri.androidlearning.clean.core.usecase;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;
import com.amirbahadoramiri.androidlearning.clean.core.repo.NoteRepository;

import java.util.List;

public class GetAllNote {

    private NoteRepository noteRepository;

    public GetAllNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> get_all() {
        return noteRepository.get_all();
    }

}
