package com.r2s.notemanagementsystem.repository;

import android.content.Context;

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
            mUserDao.insert(user);
        });
    }

    public void updateUser(User user) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mUserDao.update(user);
        });
    }
}
