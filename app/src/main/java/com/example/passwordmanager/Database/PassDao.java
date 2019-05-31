package com.example.passwordmanager.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PassDao {
    @Query("SELECT * FROM pass")
    List<Pass> getAll();

    @Insert
    void insert(Pass pass);

    @Delete
    void delete(Pass pass);

    @Update
    void update(Pass pass);
}
