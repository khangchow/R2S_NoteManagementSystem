package com.r2s.demo.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.r2s.demo.dao.PriorityDao;
import com.r2s.demo.local.AppDatabase;
import com.r2s.demo.model.Priority;

import java.util.List;
import java.util.concurrent.Executors;

public class PriorityRepository {
    private AppDatabase mDb;
    private PriorityDao mPriorityDao;

    public PriorityRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);
        this.mPriorityDao = mDb.getPriorityDao();
    }

    public LiveData<List<Priority>> getAllPriorities() {
        return mPriorityDao.getAll();
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
