package com.r2s.notemanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.User;

/**
 *
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE uid IN (:userId)")
    User loadUserById(int userId);

    @Query("SELECT * FROM users WHERE email = (:email)")
    User loadUserByEmail(String email);

    @Query("SELECT * FROM users WHERE email=(:email) AND password=(:password)")
    User login(String email, String password);

    @Insert
    void register(User user);

    @Update
    void update(User user);
}
