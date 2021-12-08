package com.r2s.demo.view.slidemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.r2s.demo.R;
import com.r2s.demo.constant.UserConstant;
import com.r2s.demo.databinding.ActivityMainBinding;
import com.r2s.demo.model.User;
import com.r2s.demo.utils.AppPrefsUtils;
import com.r2s.demo.view.LoginActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private ActivityMainBinding binding;
    private NavController navController;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        navController = Navigation.findNavController(this
                , R.id.activity_main_fragment_container);

        setSupportActionBar(binding.activityMainToolbar);

        setUpNavigationDrawerMenu();

        setUserDataToHeader();
    }

    /**
     * This method receives data from login avtivity and set them to views
     */
    private void setUserDataToHeader() {
        user = new Gson().fromJson(AppPrefsUtils.getString(UserConstant.KEY_USER_DATA), User.class);

        View headerView = binding.activityMainNavView.getHeaderView(0);

        TextView navTvGmail = headerView.findViewById(R.id.nav_header_tv_gmail);

        navTvGmail.setText(user.getEmail());
    }

    /**
     * This method set up navigation drawer mewnu
     */
    private void setUpNavigationDrawerMenu() {
        binding.activityMainNavView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , binding.activityMainDrawerLayout, binding.activityMainToolbar
                , R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        binding.activityMainDrawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        //set default selected item
        binding.activityMainNavView.setCheckedItem(R.id.slide_menu_nav_home);

        getSupportActionBar().setTitle(navController.getCurrentDestination().getLabel());

        binding.activityMainNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.slide_menu_nav_home:
                navController.navigateUp();

                break;

            case R.id.slide_menu_nav_category:
                navController.navigateUp();

                navController.navigate(R.id.action_homeFragment_to_categoryFragment);

                break;

            case R.id.slide_menu_nav_priority:
                navController.navigate(R.id.action_homeFragment_to_priorityFragment);

                break;

            case R.id.slide_menu_nav_status:
                navController.navigate(R.id.action_homeFragment_to_statusFragment);

                break;
        }

        binding.activityMainToolbar
                .setTitle(navController.getCurrentDestination().getLabel());

        binding.activityMainDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.activityMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.activityMainDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            Intent intent = new Intent(this, LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

            AppPrefsUtils.putString(UserConstant.KEY_USER_DATA, null);

            startActivity(intent);
        }
    }
}