package com.r2s.notemanagementsystem.repository;

import android.content.Context;

import com.r2s.notemanagementsystem.dao.StatusDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Status;

import java.util.concurrent.Executors;

public class StatusRepository {
    private AppDatabase mDb;
    private StatusDao mStatusDao;

    public StatusRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);
        this.mStatusDao = mDb.getStatusDao();
    }

    public void insertStatus(Status status) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mStatusDao.insertStatus(status);
        });
    }

    public void updateStatus(Status status) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mStatusDao.updateStatus(status);
        });
    }

    public void deleteStatus(Status status) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mStatusDao.deleteStatus(status);
        });
    }
}
