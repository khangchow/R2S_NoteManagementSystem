package com.r2s.notemanagementsystem.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.view.slidemenu.MainActivity;
import com.r2s.notemanagementsystem.constants.AppExecutors;
import com.r2s.notemanagementsystem.constants.Constants;
import com.r2s.notemanagementsystem.databinding.ActivityLoginBinding;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.User;

public class LoginActivity extends AppCompatActivity {
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
                        User mUser = mDb.userDao().login(user.getEmail(), user.getPassword());

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