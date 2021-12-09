package com.r2s.notemanagementsystem.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.dao.StatusDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Status;

import java.util.List;
import java.util.concurrent.Executors;

public class StatusRepository {
    private AppDatabase mDb;
    private StatusDao mStatusDao;
    private LiveData<List<Status>> mStatuses;

    // Replace with SharedPreferences user id
    private int userId = 1;

    public StatusRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);

        this.mStatusDao = mDb.getStatusDao();

        this.mStatuses = mStatusDao.getAll();
    }

    public LiveData<List<Status>> getAllStatuses() {
        return mStatuses;
    }

    public LiveData<List<Status>> getAllStatusesByUserId(int userId) {
        return mStatuses;
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
