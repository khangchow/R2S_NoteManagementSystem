package com.r2s.notemanagementsystem.view.slidemenu.view.slidemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.databinding.FragmentChangePasswordBinding;
import com.r2s.notemanagementsystem.databinding.FragmentEditProfileBinding;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.viewmodel.UserViewModel;

import java.util.List;


public class ChangePasswordFragment extends Fragment implements View.OnClickListener {

    private FragmentChangePasswordBinding binding;
    private UserViewModel mUserViewModel;
    private AppDatabase mDb;
    private User mUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChangePasswordBinding.inflate(getLayoutInflater());
        binding.btnChangePassword.setOnClickListener(this);
        binding.btnHomeChangePassword.setOnClickListener(this);
        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//            setOnClick();
    }

    private void setOnClick() {
        binding.btnChangePassword.setOnClickListener(this);
        binding.btnHomeChangePassword.setOnClickListener(this);
    }

    private void updatePassword() {


        String strPassword = binding.fragmentChangePasswordEtNew.getText().toString().trim();

        if(binding.fragmentChangePasswordEtNew.getText().toString().trim() == binding.fragmentChangePasswordEtAgain.getText().toString().trim()
        && isPasswordExit(mUser)){
            mUser.setPassword(strPassword);
            AppDatabase.getInstance(getContext()).getUserDao().changePassword(mUser);
            Toast.makeText(getActivity(),"Change password successfully",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(),"Change password error",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isPasswordExit(User user){
        List<User> list = AppDatabase.getInstance(getContext()).getUserDao().checkUser(user.getPassword());
        return list.isEmpty();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_password:
                updatePassword();
                break;
            case R.id.btn_home_change_password:
                Intent intent = new Intent(getActivity().getApplicationContext(),HomeFragment.class);
                startActivity(intent);
                break;

        }
    }
}