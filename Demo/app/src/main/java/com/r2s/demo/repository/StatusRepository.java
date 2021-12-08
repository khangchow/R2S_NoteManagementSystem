package com.r2s.demo.repository;

import android.content.Context;

import com.r2s.demo.dao.StatusDao;
import com.r2s.demo.local.AppDatabase;
import com.r2s.demo.model.Status;

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
