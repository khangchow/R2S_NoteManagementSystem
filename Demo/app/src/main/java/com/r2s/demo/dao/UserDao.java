package com.r2s.demo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.demo.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser (User user);

    @Update
    void updateUser (User user);

    @Query("Select * from user_table")
    LiveData<List<User>> getAllUsers();
}
