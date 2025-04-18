package com.r2s.notemanagementsystem.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.constant.Constants;
import com.r2s.notemanagementsystem.constant.UserConstant;
import com.r2s.notemanagementsystem.databinding.ActivityRegisterBinding;
import com.r2s.notemanagementsystem.model.User;
import com.r2s.notemanagementsystem.viewmodel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private UserViewModel userViewModel;
    private Boolean isExist = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        initEvents();
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
        String passwordConfirm = binding.activityRegisterEtPasswordConfirm
                .getText().toString().trim();

        if(!user.getEmail().matches(Constants.emailPattern)){
            binding.activityRegisterEtEmail.setError(getResources().getString(R.string.et_email_invalid));
            return false;
        }
        if(TextUtils.isEmpty(user.getPassword())){
            binding.activityRegisterEtPassword.setError(getResources().getString(R.string.et_pwd_invalid));
            return false;
        }
        if(TextUtils.isEmpty(passwordConfirm)
                || !TextUtils.equals(user.getPassword(),passwordConfirm)){
            binding.activityRegisterEtPasswordConfirm.setError(getResources().getString(R.string.et_pwd_confirm_invalid));
            return false;
        }

        return true;
    }

    /**
     * This method created to register events
     */
    public void initEvents(){
        binding.activityRegisterBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();

                user.setEmail(binding.activityRegisterEtEmail.getText().toString().trim());

                user.setPassword(binding.activityRegisterEtPassword.getText().toString().trim());

                if(validateInput(user)){
                    userViewModel.count(user.getEmail())
                            .observe(RegisterActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(@Nullable Integer integer) {
                            if(integer<1){
                                userViewModel.insertUser(user);

                                isExist = false;

                                Toast.makeText(RegisterActivity.this
                                        , getResources().getString(R.string.user_created), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);

                                intent.putExtra(UserConstant.KEY_GMAIL
                                        , binding.activityRegisterEtEmail.getText().toString());

                                intent.putExtra(UserConstant.KEY_PASS
                                        , binding.activityRegisterEtPassword.getText().toString());

                                startActivity(intent);

                                finish();
                            }
                            if(isExist == true)
                                binding.activityRegisterEtEmail.setError(getResources().getString(R.string.et_email_exists));
                        }
                    });
                }
            }
        });
    }
}