package com.r2s.notemanagementsystem.view.slidemenu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.databinding.ActivityLoginBinding;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;
import com.r2s.notemanagementsystem.view.slidemenu.view.slidemenu.MainActivity;
import com.r2s.notemanagementsystem.viewmodel.UserViewModel;
import com.r2s.notemanagementsystem.constant.Constants;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {
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
                User user = new User(0, binding.editTextTextPersonName.getText().toString()
                        , binding.editTextTextPersonName2.getText().toString());

                AppPrefsUtils.putString(Constants.KEY_USER_DATA, new Gson().toJson(user));

                userViewModel.insertUser(user);

                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

}