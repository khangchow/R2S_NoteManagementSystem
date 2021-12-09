package com.r2s.notemanagementsystem.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.User;

/**
 *
 */
@Dao
public interface UserDao {
    @Query("SELECT COUNT() FROM user_table WHERE email = (:email)")
    LiveData<Integer> count(String email);

    @Query("SELECT * FROM user_table WHERE email=(:email) AND pass=(:password)")
    LiveData<User> login(String email, String password);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Update
    void updateUser(User user);
}