package com.r2s.notemanagementsystem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.model.Priority;
import com.r2s.notemanagementsystem.repository.PriorityRepository;

import java.util.List;

public class PriorityViewModel extends AndroidViewModel {
    private PriorityRepository mPriorityRepository;
    private LiveData<List<Priority>> mPriorities;

    public PriorityViewModel(@NonNull Application application) {
        super(application);
        this.mPriorityRepository = new PriorityRepository(application);
        this.mPriorities = mPriorityRepository.getAllPriorities();
    }

    public LiveData<List<Priority>> getAllPriorities() {
        return mPriorities;
    }

    public void insertPriority(Priority priority) {
        mPriorityRepository.insertPriority(priority);
    }

    public void updatePriority(Priority priority) {
        mPriorityRepository.updatePriority(priority);
    }

    public void deletePriority(Priority priority) {
        mPriorityRepository.deletePriority(priority);
    }
}
