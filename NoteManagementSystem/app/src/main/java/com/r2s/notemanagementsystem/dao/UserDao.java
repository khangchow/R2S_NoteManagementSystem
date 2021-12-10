package com.r2s.notemanagementsystem.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.r2s.notemanagementsystem.model.User;

import java.util.List;

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

    @Query("SELECT * FROM users WHERE uid IN (:userId)")
    User loadUserById(int userId);

    @Query("SELECT * FROM users WHERE email = (:email)")
    User loadUserByEmail(String email);

    // @Query("SELECT * FROM users WHERE email=(:email) AND password=(:password)")
    // User login(String email, String password);

    // @Insert
    // void register(User user);

    // @Insert
    // void insert(User user);
    // @Update
    // void update(User user);



    @Update
    void changePassword(User user);

    //2 cái getlistuser với checkuser này có trả về kết quả nên phải dùng livedata nha 
    @Query("SELECT * FROM users")
    List<User> getListUser();

    @Query("SELECT * FROM users WHERE password= :password")
    List<User> checkUser(String password);
}