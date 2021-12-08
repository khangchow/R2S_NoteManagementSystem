package com.r2s.notemanagementsystem.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.constants.AppExecutors;
import com.r2s.notemanagementsystem.constants.Constants;
import com.r2s.notemanagementsystem.dao.UserDao;
import com.r2s.notemanagementsystem.databinding.ActivityRegisterBinding;
import com.r2s.notemanagementsystem.local.AppDatabase;
import com.r2s.notemanagementsystem.model.User;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.activityRegisterBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(binding.activityRegisterEtEmail.getText().toString().trim());
                user.setPassword(binding.activityRegisterEtPassword.getText().toString().trim());

                if(validateInput(user)){
                    mDb = AppDatabase.getInstance(getBaseContext());
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.userDao().register(user);
                        }
                    });
                }
            }
        });
    }

    /**
     * This method created to show login activity
     * @params v
     * @return void
     */
    public void showLogin(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * This method created to check inputs filed & display errors
     * @param
     * @return
     */
    private Boolean validateInput(User user){
        String passwordConfirm = binding.activityRegisterEtPasswordConfirm.getText().toString().trim();

        if(!user.getEmail().matches(Constants.emailPattern)){
            binding.activityRegisterEtEmail.setError(getResources().getString(R.string.et_email_invalid));
            return false;
        }
        if(TextUtils.isEmpty(user.getPassword())){
            binding.activityRegisterEtPassword.setError(getResources().getString(R.string.et_pwd_invalid));
            return false;
        }
        if(TextUtils.isEmpty(passwordConfirm)||!TextUtils.equals(user.getPassword(),passwordConfirm)){
            binding.activityRegisterEtPasswordConfirm.setError(getResources().getString(R.string.et_pwd_confirm_invalid));
            return false;
        }

        return true;
    }
}