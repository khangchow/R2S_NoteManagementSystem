package com.r2s.notemanagementsystem.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mUserRepo;

    public UserViewModel(@NonNull Application application) {
        super(application);
        this.mUserRepo = new UserRepository(application);
    }

    public void insertUser(User user) {
        mUserRepo.insertUser(user);
    }

    public void updateUser(User user) {
        mUserRepo.updateUser(user);
    }

    public LiveData<Integer> count(String email){ return mUserRepo.count(email); }

    public LiveData<User> login(String email, String pass){return mUserRepo.login(email, pass);}
}
