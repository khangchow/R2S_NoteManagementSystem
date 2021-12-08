package com.r2s.demo.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.r2s.demo.dao.UserDao;
import com.r2s.demo.local.AppDatabase;
import com.r2s.demo.model.User;

import java.util.List;
import java.util.concurrent.Executors;

public class UserRepository {
    private AppDatabase mDB;
    private UserDao mUserDao;
    private LiveData<List<User>> mUsers;

    public UserRepository(Context context) {
        this.mDB = AppDatabase.getInstance(context);

        this.mUserDao = mDB.getUserDao();

        mUsers = mUserDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mUsers;
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
}
