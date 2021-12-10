package com.r2s.notemanagementsystem.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.Dao.PriorityDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Priority;

import java.util.List;
import java.util.concurrent.Executors;

public class PriorityRepository {
    private AppDatabase mDb;
    private PriorityDao mPriorityDao;
    private LiveData<List<Priority>> mPriorities;

    // Replace with SharedPreferences user id
    private int userId = 1;

    /**
     * This method is used as constructor for PriorityRepository class
     * @param context
     */
    public PriorityRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);

        this.mPriorityDao = mDb.getPriorityDao();

        this.mPriorities = mPriorityDao.getAllByUserId(userId);
    }

    /**
     * This method returns all notes by current logged in user
     * @param userId int
     * @return LiveData List
     */
    public LiveData<List<Priority>> getAllPrioritiesByUserId(int userId) {
        return mPriorities;
    }

    /**
     * This method inserts a new priority
     * @param priority Priority
     */
    public void insertPriority(Priority priority) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mPriorityDao.insertPriority(priority);
        });
    }

    /**
     * This method updates a priority by id
     * @param priority Priority
     */
    public void updatePriority(Priority priority) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mPriorityDao.updatePriority(priority);
        });
    }

    /**
     * This method deletes a priority
     * @param priority Priority
     */
    public void deletePriority(Priority priority) {
        Executors.newSingleThreadScheduledExecutor().execute(() -> {
            mPriorityDao.deletePriority(priority);
        });
    }
}
