package com.r2s.notemanagementsystem.view.slidemenu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.constant.AppExecutors;
import com.r2s.notemanagementsystem.databinding.ActivityLoginBinding;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.utils.AppPrefsUtils;
import com.r2s.notemanagementsystem.viewmodel.UserViewModel;
import com.r2s.notemanagementsystem.constant.Constants;

public class LoginActivity extends AppCompatActivity  {
    ActivityLoginBinding binding;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activityLoginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(binding.activityLoginEtEmail.getText().toString().trim());
                user.setPassword(binding.activityLoginEtPassword.getText().toString().trim());

                mDb = AppDatabase.getInstance(getBaseContext());
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        User mUser = mDb.getUserDao().login(user.getEmail(), user.getPassword());

                        if (mUser == null) {
                            final Toast toast = Toast.makeText(getBaseContext(), "null", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
        });
    }

    /**
     * This method show register activity
     * @param v View class
     * @return void
     */
    public void showRegister(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}