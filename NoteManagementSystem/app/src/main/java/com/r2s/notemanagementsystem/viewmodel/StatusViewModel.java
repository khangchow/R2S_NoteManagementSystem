package com.r2s.notemanagementsystem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.constant.Constants;
import com.r2s.notemanagementsystem.model.Status;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.repository.StatusRepository;
import com.r2s.notemanagementsystem.repository.UserRepository;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;

import java.util.List;

public class StatusViewModel extends AndroidViewModel {

    private StatusRepository mStatusRepository;
    private LiveData<List<Status>> mStatuses;
    private User mUser;

    /**
     * Constructor with 1 parameter
     * @param application Application
     */
    public StatusViewModel(@NonNull Application application) {
        super(application);
        this.mStatusRepository = new StatusRepository(application);

        mUser = new Gson().fromJson(AppPrefsUtils.getString(Constants.KEY_USER_DATA), User.class);

        this.mStatuses = mStatusRepository.getAllStatusesByUserId(mUser.getUid());
    }

    /**
     * This method returns all notes by current logged in user
     * @param userId int
     * @return LiveData List
     */
    public LiveData<List<Status>> getStatusesByUserId(int userId) {
        return mStatuses;
    }

    /**
     * This method inserts a new status
     * @param status Status
     */
    public void insertStatus(Status status) {
        mStatusRepository.insertStatus(status);
    }

    /**
     * This method updates a status
     * @param status Status
     */
    public void updateStatus(Status status) {
        mStatusRepository.updateStatus(status);
    }

    /**
     * This method deletes a status
     * @param status Status
     */
    public void deleteStatus(Status status) {
        mStatusRepository.deleteStatus(status);
    }
}
