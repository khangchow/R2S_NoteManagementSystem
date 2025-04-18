package com.r2s.notemanagementsystem.repository;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.constant.Constants;
import com.r2s.notemanagementsystem.constant.UserConstant;
import com.r2s.notemanagementsystem.dao.StatusDao;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.Status;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;

import java.util.List;
import java.util.concurrent.Executors;

public class StatusRepository {
    private AppDatabase mDb;
    private StatusDao mStatusDao;
    private LiveData<List<Status>> mStatuses;
    private User mUser;

    /**
     * This method is used as constructor for StatusRepository class
     * @param context Context
     */
    public StatusRepository(Context context) {
        this.mDb = AppDatabase.getInstance(context);
        this.mStatusDao = mDb.getStatusDao();

        mUser = new Gson().fromJson(AppPrefsUtils.getString(Constants.KEY_USER_DATA), User.class);

        this.mStatuses = mStatusDao.getAllByUserId(mUser.getUid());
    }

    /**
     * This method returns all statuses by current logged in user
     * @return LiveData List
     */
    public LiveData<List<Status>> getAllStatusesByUserId() {
        return mStatuses;
    }

    /**
     * This method inserts a new priority
     * @param status Status
     */
    public void insertStatus(Status status) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mStatusDao.insertStatus(status);
        });
    }

    /**
     * This method updates a status by id
     * @param status Status
     */
    public void updateStatus(Status status) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mStatusDao.updateStatus(status);
        });
    }

    /**
     * This method deletes a priority
     * @param status Status
     */
    public void deleteStatus(Status status) {
        Executors.newSingleThreadExecutor().execute(() -> {
            mStatusDao.deleteStatus(status);
        });
    }
}
