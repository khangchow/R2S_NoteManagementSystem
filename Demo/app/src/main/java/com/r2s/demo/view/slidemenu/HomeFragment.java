package com.r2s.demo.view.slidemenu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r2s.demo.R;
import com.r2s.demo.adapter.UserRecViewAdapter;
import com.r2s.demo.databinding.FragmentHomeBinding;
import com.r2s.demo.model.User;
import com.r2s.demo.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;
    private UserViewModel mViewModel;
    private UserRecViewAdapter mAdapter;
    private List<User> mUsers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        setUpRecView();

        setUpViewModel();

        setOnClicks();
    }

    private void setOnClicks() {
        binding.btn.setOnClickListener(this);
    }

    private void setUpRecView() {
        mAdapter = new UserRecViewAdapter(mUsers);

        binding.recView.setAdapter(mAdapter);

        binding.recView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpViewModel() {
        mViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
            mAdapter.setmUsers(users);
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                mViewModel.insertUser(new User(0, binding.et.getText().toString()));
                break;
        }
    }
}