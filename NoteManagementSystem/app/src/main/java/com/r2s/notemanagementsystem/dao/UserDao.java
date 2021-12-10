package com.r2s.notemanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.User;

import java.util.List;

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

    @Insert
    void insert(User user);
    @Update
    void update(User user);

    @Update
    void updateprofile(User user);

    @Update
    void changePassword(User user);


    @Query("SELECT * FROM users")
    List<User> getListUser();

    @Query("SELECT * FROM users WHERE password= :password")
    List<User> checkUser(String password);
}
