package com.r2s.notemanagementsystem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.model.Status;
import com.r2s.notemanagementsystem.repository.StatusRepository;

import java.util.List;

public class StatusViewModel extends AndroidViewModel {

    private StatusRepository mStatusRepository;
    private LiveData<List<Status>> mStatuses;

    // Replace with SharedPreferences user id
    private int userId = 1;

    public StatusViewModel(@NonNull Application application) {
        super(application);
        this.mStatusRepository = new StatusRepository(application);

        this.mStatuses = mStatusRepository.getAllStatusesByUserId(userId);
    }

    public LiveData<List<Status>> getAllStatuses() {
        return mStatuses;
    }

    public LiveData<List<Status>> getAllStatusesByUserId(int userId) {
        return mStatuses;
    }

    public void insertStatus(Status priority) {
        mStatusRepository.insertStatus(priority);
    }

    public void updateStatus(Status priority) {
        mStatusRepository.updateStatus(priority);
    }

    public void deleteStatus(Status priority) {
        mStatusRepository.deleteStatus(priority);
    }
}
