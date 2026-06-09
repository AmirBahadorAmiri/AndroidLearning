package com.amirbahadoramiri.androidlearning.clean.framework.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(NoteEntity noteEntity);

    @Query("SELECT * FROM notes WHERE id = :id")
    NoteEntity get(Long id);

    @Query("SELECT * FROM notes")
    List<NoteEntity> get_all();

    @Delete
    void remove(NoteEntity noteEntity);

}
