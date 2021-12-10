package com.r2s.demo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.r2s.demo.model.Status;
import com.r2s.demo.repository.StatusRepository;

public class StatusViewModel extends AndroidViewModel {
    private StatusRepository mStatusRepo;

    public StatusViewModel(@NonNull Application application) {
        super(application);
        this.mStatusRepo = new StatusRepository(application);
    }

    public void insertStatus(Status status) {
        mStatusRepo.insertStatus(status);
    }

    public void updateStatus(Status status) {
        mStatusRepo.updateStatus(status);
    }

    public void deleteStatus(Status status) {
        mStatusRepo.deleteStatus(status);
    }
}
