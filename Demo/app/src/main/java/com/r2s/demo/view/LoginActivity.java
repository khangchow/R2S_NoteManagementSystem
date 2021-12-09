package com.r2s.demo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.r2s.demo.R;
import com.r2s.demo.constant.UserConstant;
import com.r2s.demo.databinding.ActivityLoginBinding;
import com.r2s.demo.local.AppPrefs;
import com.r2s.demo.model.User;
import com.r2s.demo.utils.AppPrefsUtils;
import com.r2s.demo.view.slidemenu.MainActivity;
import com.r2s.demo.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        AppPrefsUtils.createAppPrefs(this);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        setOnClicks();
    }

    private void setOnClicks() {
        binding.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                User user = new User(0, binding.editTextTextPersonName.getText().toString());

                AppPrefsUtils.putString(UserConstant.KEY_USER_DATA, new Gson().toJson(user));

                userViewModel.insertUser(user);

                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}