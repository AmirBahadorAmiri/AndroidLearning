package com.amirbahadoramiri.androidlearning.clean.core.usecase;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;
import com.amirbahadoramiri.androidlearning.clean.core.repo.NoteRepository;

public class AddNote {

    private NoteRepository noteRepository;

    public AddNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void add(Note note) {
        noteRepository.add(note);
    }

}
