package com.amirbahadoramiri.androidlearning.clean.framework;

import com.amirbahadoramiri.androidlearning.clean.core.usecase.AddNote;
import com.amirbahadoramiri.androidlearning.clean.core.usecase.GetAllNote;
import com.amirbahadoramiri.androidlearning.clean.core.usecase.GetNote;
import com.amirbahadoramiri.androidlearning.clean.core.usecase.RemoveNote;

public class UseCases {

    public AddNote addNote;
    public GetAllNote getAllNote;
    public GetNote getNote;
    public RemoveNote removeNote;

    public UseCases(AddNote addNote, GetAllNote getAllNote, GetNote getNote, RemoveNote removeNote) {
        this.addNote = addNote;
        this.getAllNote = getAllNote;
        this.getNote = getNote;
        this.removeNote = removeNote;
    }

    public AddNote getAddNote() {
        return addNote;
    }

    public void setAddNote(AddNote addNote) {
        this.addNote = addNote;
    }

    public GetAllNote getGetAllNote() {
        return getAllNote;
    }

    public void setGetAllNote(GetAllNote getAllNote) {
        this.getAllNote = getAllNote;
    }

    public GetNote getGetNote() {
        return getNote;
    }

    public void setGetNote(GetNote getNote) {
        this.getNote = getNote;
    }

    public RemoveNote getRemoveNote() {
        return removeNote;
    }

    public void setRemoveNote(RemoveNote removeNote) {
        this.removeNote = removeNote;
    }
}
