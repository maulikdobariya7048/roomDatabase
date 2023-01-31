package com.example.roomdatabase;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = StudentModel.class, exportSchema = false, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static final String DATABASE_NAME = "studentDb";
    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getDb(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseHelper.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract StudentDao studentDao();
}
