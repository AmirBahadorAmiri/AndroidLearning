package com.amirbahadoramiri.androidlearning.clean.framework.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NoteEntity.class}, version = 1)
public abstract class DatabaseServices extends RoomDatabase {

    private static DatabaseServices databaseServices;

    public static DatabaseServices create(Context context, String dbname) {
        return Room.databaseBuilder(context, DatabaseServices.class, dbname)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration(false)
                .build();
    }

    public static DatabaseServices getDatabaseServices(Context context) {
        if (databaseServices == null)
            databaseServices = create(context, "note.db");
        return databaseServices;
    }

    public abstract NoteDao getDao();

}
