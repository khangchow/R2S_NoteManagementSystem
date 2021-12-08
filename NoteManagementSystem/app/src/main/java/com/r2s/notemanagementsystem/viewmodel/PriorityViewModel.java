package com.r2s.notemanagementsystem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.r2s.notemanagementsystem.model.Priority;
import com.r2s.notemanagementsystem.repository.PriorityRepository;

public class PriorityViewModel extends AndroidViewModel {
    private PriorityRepository mPriorityRepo;

    public PriorityViewModel(@NonNull Application application) {
        super(application);
        this.mPriorityRepo = new PriorityRepository(application);
    }

    public void insertPriority(Priority priority) {
        mPriorityRepo.insertPriority(priority);
    }

    public void updatePriority(Priority priority) {
        mPriorityRepo.updatePriority(priority);
    }

    public void deletePriority(Priority priority) {
        mPriorityRepo.deletePriority(priority);
    }
}
