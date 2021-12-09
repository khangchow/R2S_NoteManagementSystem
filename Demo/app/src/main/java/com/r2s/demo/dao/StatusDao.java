package com.r2s.demo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.demo.model.Status;

import java.util.List;

@Dao
public interface StatusDao {
    @Query("SELECT * FROM status_table")
    List<Status> getAll();

    @Insert
    void insertStatus(Status status);

    @Update
    void updateStatus(Status status);

    @Delete
    void deleteStatus(Status status);
}
