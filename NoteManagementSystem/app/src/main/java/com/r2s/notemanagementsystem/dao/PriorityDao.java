package com.r2s.notemanagementsystem.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.Priority;

import java.util.List;

@Dao
public interface PriorityDao {
    @Query("SELECT * FROM priority_table")
    LiveData<List<Priority>> getAll();

    @Insert
    void insertPriority(Priority priority);

    @Update
    void updatePriority(Priority priority);

    @Delete
    void deletePriority(Priority priority);
}
