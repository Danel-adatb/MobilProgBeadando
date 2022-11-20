package com.beadando.xuxejo.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.beadando.xuxejo.model.MegvehetoAuto;

@Database(entities = {MegvehetoAuto.class}, version = 1)
public abstract class MegvehetoAutoDB extends RoomDatabase {
    public abstract MegvehetoAutoDao megvehetoAutoDao();
}
