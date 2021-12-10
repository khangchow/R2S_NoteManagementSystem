package com.r2s.notemanagementsystem.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.Status;

import java.util.List;

@Dao
public interface StatusDao {
    @Query("SELECT * FROM status_table")
    LiveData<List<Status>> getAll();

    @Query("SELECT * FROM status_table WHERE userId IN (:user_id)")
    LiveData<List<Status>> getAllByUserId(int user_id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStatus(Status status);

    @Update
    void updateStatus(Status status);

    @Delete
    void deleteStatus(Status status);
}
