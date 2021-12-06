package com.r2s.notemanagementsystem.slidemenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.r2s.notemanagementsystem.R;
import com.r2s.notemanagementsystem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        navController = Navigation.findNavController(this
                , R.id.activity_main_fragment_container);

        setSupportActionBar(binding.activityMainToolbar);

        setUpNavigationDrawerMenu();
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

        binding.activityMainToolbar.setTitle(navController.getCurrentDestination().getLabel());

        binding.activityMainNavView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

        }

        binding.activityMainDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (binding.activityMainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.activityMainDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}