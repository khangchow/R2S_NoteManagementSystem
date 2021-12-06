package com.r2s.demo.repository;

import android.content.Context;

import com.r2s.demo.dao.UserDao;
import com.r2s.demo.local.AppDatabase;
import com.r2s.demo.model.User;

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
}
