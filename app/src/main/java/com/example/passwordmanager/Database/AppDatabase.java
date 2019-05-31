package com.example.passwordmanager.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Pass.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PassDao passDao();
}