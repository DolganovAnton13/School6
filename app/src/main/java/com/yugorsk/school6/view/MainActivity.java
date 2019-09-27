package com.yugorsk.school6.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;


import com.google.android.material.navigation.NavigationView;

import com.yugorsk.school6.view.fragment.FragmentAboutSchool;
import com.yugorsk.school6.view.fragment.FragmentMain;
import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.ActivityMainBinding;
import com.yugorsk.school6.view.fragment.FragmentSchedule;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavController navController;
    ActivityMainBinding binding;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        navigationView = findViewById(R.id.navigationView);
        navigationView.getMenu().getItem(0).setChecked(true);
        //navController = Navigation.findNavController(this, R.id.hostFragment);

        //NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.hostFragment.getId(), new FragmentMain());
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment selectedFragment = null;
        menuItem.setChecked(true);

        binding.drawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id) {

            case R.id.Main:
                //navController.navigate(R.id.Main);
                selectedFragment = new FragmentMain();
                break;
            case R.id.AboutSchool:
                //navController.navigate(R.id.AboutSchool);
                selectedFragment = new FragmentAboutSchool();
                break;
            case R.id.Schedule:
                selectedFragment=new FragmentSchedule();
                break;
            default:
                selectedFragment = new FragmentMain();
                break;

        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(binding.hostFragment.getId(), selectedFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        return true;
    }

    public void setToolbar(Toolbar toolbar, String title) {
        if (toolbar != null) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, binding.drawerLayout, toolbar, R.string.main, R.string.close);

            binding.drawerLayout.setDrawerListener(toggle);
            toggle.syncState();
        } else {
            binding.drawerLayout.setDrawerListener(null);
        }
    }

    @Override
    public void onBackPressed() {

        if (binding.drawerLayout != null && binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(Gravity.LEFT);
        else {

            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0)
                super.onBackPressed();
            else
                getSupportFragmentManager().popBackStack();
        }
    }
}
