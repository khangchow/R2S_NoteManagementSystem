package com.r2s.notemanagementsystem.repository;

import android.content.Context;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.r2s.notemanagementsystem.dao.UserDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.User;

import java.util.concurrent.Executors;

public class UserRepository {
    private AppDatabase mDB;
    private UserDao mUserDao;

    public UserRepository(Context context) {
        this.mDB = AppDatabase.getInstance(context);

        this.mUserDao = mDB.getUserDao();
    }

    public void insertUser(User user) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mUserDao.insertUser(user);
        });
    }

    public void updateUser(User user) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mUserDao.updateUser(user);
        });
    }

    public LiveData<Integer> count(String email){
        return mUserDao.count(email);
    }

    public LiveData<User> login(String email, String pass){
        return mUserDao.login(email, pass);
    }
}