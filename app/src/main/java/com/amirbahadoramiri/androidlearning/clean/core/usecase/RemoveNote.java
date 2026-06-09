package com.amirbahadoramiri.androidlearning.clean.core.usecase;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;
import com.amirbahadoramiri.androidlearning.clean.core.repo.NoteRepository;

public class RemoveNote {

    private NoteRepository noteRepository;

    public RemoveNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void remove(Note note) {
        noteRepository.remove(note);
    }

}
