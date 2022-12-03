package com.beadando.xuxejo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Car.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CarDAO carDAO();
    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context c) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(c.getApplicationContext(), AppDatabase.class, "cars")
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
}
