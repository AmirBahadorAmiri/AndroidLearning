package com.amirbahadoramiri.androidlearning.tools.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.amirbahadoramiri.androidlearning.newmodels.Company;
import com.amirbahadoramiri.androidlearning.newmodels.CompanyConverter;

@Database(entities = {Company.class}, version = 1, exportSchema = false)
@TypeConverters({CompanyConverter.class})
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB roomDB;

    public static RoomDB getInstance(Context context) {
        if (roomDB == null) {
            roomDB = Room.databaseBuilder(context, RoomDB.class, "room_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration(false)
                    .build();
        }
        return roomDB;
    }

    public abstract CompanyDAO companyDAO();

}
