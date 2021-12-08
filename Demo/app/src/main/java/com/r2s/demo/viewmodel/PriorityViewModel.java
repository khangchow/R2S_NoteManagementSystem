package com.r2s.demo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.r2s.demo.model.Priority;
import com.r2s.demo.repository.PriorityRepository;

import java.util.List;

public class PriorityViewModel extends AndroidViewModel {
    private PriorityRepository mPriorityRepo;
    private LiveData<List<Priority>> mPriorities;

    public PriorityViewModel(@NonNull Application application) {
        super(application);
        this.mPriorityRepo = new PriorityRepository(application);
    }

    public LiveData<List<Priority>> getAllPriorities() {
        return mPriorities;
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
