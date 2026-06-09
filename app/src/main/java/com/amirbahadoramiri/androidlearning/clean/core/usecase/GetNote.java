package com.amirbahadoramiri.androidlearning.clean.core.usecase;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;
import com.amirbahadoramiri.androidlearning.clean.core.repo.NoteRepository;

public class GetNote {

    private NoteRepository noteRepository;

    public GetNote(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note get(Long id) {
        return noteRepository.get(id);
    }

}
