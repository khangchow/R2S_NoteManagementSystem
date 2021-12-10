package com.r2s.notemanagementsystem.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.constant.Constants;
import com.r2s.notemanagementsystem.dao.PriorityDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Priority;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;

import java.util.List;
import java.util.concurrent.Executors;

public class PriorityRepository {
    private AppDatabase mDb;
    private PriorityDao mPriorityDao;
    private LiveData<List<Priority>> mPriorities;
    private User mUser;

    /**
     * This method is used as constructor for PriorityRepository class
     * @param context Context
     */
    public PriorityRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);
        this.mPriorityDao = mDb.getPriorityDao();

        mUser = new Gson().fromJson(AppPrefsUtils.getString(Constants.KEY_USER_DATA), User.class);

        this.mPriorities = mPriorityDao.getAllByUserId(mUser.getUid());
    }

    /**
     * This method returns all priorities by current logged in user
     * @return LiveData List
     */
    public LiveData<List<Priority>> getAllPrioritiesByUserId() {
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
