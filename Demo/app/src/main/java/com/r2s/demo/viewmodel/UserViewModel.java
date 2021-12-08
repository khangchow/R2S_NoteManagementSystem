package com.r2s.demo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.r2s.demo.model.User;
import com.r2s.demo.repository.UserRepository;

import java.util.List;
import java.util.concurrent.Executors;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mUserRepo;

    private LiveData<List<User>> mUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.mUserRepo = new UserRepository(application);

        this.mUsers = mUserRepo.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mUsers;
    }

    public void insertUser(User user) {
        mUserRepo.insertUser(user);
    }

    public void updateUser(User user) {
        mUserRepo.updateUser(user);
    }
}
