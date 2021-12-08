package com.r2s.notemanagementsystem.repository;

import android.content.Context;

import com.r2s.notemanagementsystem.dao.PriorityDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Priority;

import java.util.concurrent.Executors;

public class PriorityRepository {
    private AppDatabase mDb;
    private PriorityDao mPriorityDao;

    public PriorityRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);
        this.mPriorityDao = mDb.getPriorityDao();
    }

    public void insertPriority(Priority priority) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mPriorityDao.insertPriority(priority);
        });
    }

    public void updatePriority(Priority priority) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mPriorityDao.updatePriority(priority);
        });
    }

    public void deletePriority(Priority priority) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mPriorityDao.deletePriority(priority);
        });
    }
}
