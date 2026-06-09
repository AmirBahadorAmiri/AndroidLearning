package com.amirbahadoramiri.androidlearning.clean.framework;

import android.content.Context;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;
import com.amirbahadoramiri.androidlearning.clean.core.repo.NoteDataSource;
import com.amirbahadoramiri.androidlearning.clean.framework.db.DatabaseServices;
import com.amirbahadoramiri.androidlearning.clean.framework.db.NoteDao;
import com.amirbahadoramiri.androidlearning.clean.framework.db.NoteEntity;

import java.util.ArrayList;
import java.util.List;

public class RoomNoteDataSource implements NoteDataSource {

    private NoteDao noteDao;

    public RoomNoteDataSource(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public void add(Note note) {
        noteDao.add(NoteEntity.fromNote(note));
    }

    @Override
    public Note get(Long id) {
        return noteDao.get(id).toNote();
    }

    @Override
    public List<Note> get_all() {
        List<Note> notes = new ArrayList<>();
        List<NoteEntity> noteEntities = noteDao.get_all();
        for (NoteEntity noteEntity : noteEntities) notes.add(noteEntity.toNote());
        return notes;
    }

    @Override
    public void remove(Note note) {
        noteDao.remove(NoteEntity.fromNote(note));
    }
}
