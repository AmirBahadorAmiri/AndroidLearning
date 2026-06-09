package com.amirbahadoramiri.androidlearning.clean.framework.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.amirbahadoramiri.androidlearning.clean.core.data.Note;

@Entity(tableName = "notes")
public class NoteEntity {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String title, content;
    private Long createdTime, updatedTime;

    public NoteEntity(String title, String content, Long createdTime, Long updatedTime) {
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Note toNote() {
        return new Note(getId(), getTitle(), getContent(), getCreatedTime(), getUpdatedTime());
    }

    public static NoteEntity fromNote(Note note) {
        return new NoteEntity(note.getTitle(), note.getContent(), note.getCreatedTime(), note.getUpdatedTime());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }
}
